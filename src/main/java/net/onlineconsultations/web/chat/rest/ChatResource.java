package net.onlineconsultations.web.chat.rest;

import net.onlineconsultations.domain.Chat;
import net.onlineconsultations.domain.ChatMessage;
import net.onlineconsultations.domain.Consultant;
import net.onlineconsultations.service.ChatService;
import net.onlineconsultations.service.ConsultantService;
import net.onlineconsultations.web.chat.rest.model.ChatInfo;
import net.onlineconsultations.web.chat.rest.model.ChatMessageInfo;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/chat")
public class ChatResource {
    private static final Logger log = LoggerFactory.getLogger(ChatResource.class);

    @Inject
    private ChatService chatService;

    @Inject
    private ConsultantService consultantService;

    @RolesAllowed("ROLE_CONSULTANT")
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ChatInfo getActualChatInfo(Principal principal,
                                      HttpSession session) {
        Chat availableChat = null;
        if (principal != null) {
            Consultant consultant = consultantService.getByUsername(principal.getName());

            availableChat = chatService.findActiveChatWithConsultant(consultant);
        } else {
            String sessionId = (String) session.getAttribute("chatSessionId");

            if (sessionId == null) {
                availableChat = null;
            } else {
                availableChat = chatService.findBySessionId(sessionId);
            }
        }

        if (availableChat == null) {
            return new ChatInfo();
        } else {
            return ChatInfo.of(availableChat);
        }
    }

    @RolesAllowed({"ROLE_CONSULTANT", "ROLE_ANONYMOUS"})
    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    @ResponseBody
    public List<ChatMessageInfo> getLastMessages(@RequestParam("lastMessageId") Long lastMessageId,
                                                 Principal principal,
                                                 HttpSession session) {
        Chat activeChat = null;
        if (principal != null) {

            Consultant consultant = consultantService.getByUsername(principal.getName());
            activeChat = chatService.findActiveChatWithConsultant(consultant);

        } else {
            String chatSessionId = (String) session.getAttribute("chatSessionId");
            activeChat = chatService.findBySessionId(chatSessionId);
        }

        if (activeChat == null) {
            throw new RuntimeException("There is no active chat for this user");
        }

        return getLastMessages(activeChat, lastMessageId);
    }

    private List<ChatMessageInfo> getLastMessages(Chat chat, Long lastMessageId) {
        List<ChatMessageInfo> chatMessageInfos = new ArrayList<>();

        List<ChatMessage> chatMessages = chatService.getLastMessagesInChat(chat, lastMessageId);

        for (ChatMessage chatMessage : chatMessages) {
            chatMessageInfos.add(ChatMessageInfo.of(chatMessage));
        }

        return chatMessageInfos;
    }

    @RolesAllowed({"ROLE_CONSULTANT", "ROLE_ANONYMOUS"})
    @RequestMapping(value = "/messages", method = RequestMethod.POST)
    public ResponseEntity<String> postMessage(@RequestBody @Valid ChatMessageInfo chatMessageInfo,
                                              Principal principal,
                                              HttpSession session) {
        try {
            Consultant consultant = null;
            Chat activeChat = null;
            if (principal == null) {
                // anonymous
                String chatSessionId = (String) session.getAttribute("chatSessionId");
                activeChat = chatService.findBySessionId(chatSessionId);
            } else {
                // consultant
                consultant = consultantService.getByUsername(principal.getName());
                activeChat = chatService.findActiveChatWithConsultant(consultant);
            }

            if (activeChat == null) {
                throw new RuntimeException("There is no active chat for this user");
            }

            ChatMessage chatMessage = new ChatMessage(
                    chatMessageInfo.getBody(),
                    LocalDateTime.now(DateTimeZone.UTC),
                    activeChat,
                    consultant
            );
            chatService.postNewMessage(chatMessage);
            return new ResponseEntity<>("", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
