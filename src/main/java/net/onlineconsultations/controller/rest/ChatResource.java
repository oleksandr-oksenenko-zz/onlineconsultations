package net.onlineconsultations.controller.rest;

import net.onlineconsultations.controller.rest.model.ChatInfo;
import net.onlineconsultations.controller.rest.model.ChatMessageInfo;
import net.onlineconsultations.domain.Chat;
import net.onlineconsultations.domain.ChatMessage;
import net.onlineconsultations.domain.User;
import net.onlineconsultations.service.ChatService;
import net.onlineconsultations.service.UserService;
import org.apache.log4j.Logger;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/chat")
public class ChatResource {
    private static final Logger log = Logger.getLogger(ChatResource.class);

    @Inject
    private ChatService chatService;

    @Inject
    private UserService userService;

    @RolesAllowed("ROLE_CONSULTANT")
    @RequestMapping(value = "/poll_for_chat", method = RequestMethod.POST)
    @ResponseBody
    public ChatInfo consultantPollForChat(Principal principal) {
        User consultant = userService.findByUsername(principal.getName());

        Chat activeChat = chatService.findActiveChatWithConsultant(consultant);
        if (activeChat == null) {
            return new ChatInfo(-1L);
        }
        chatService.setConsultantInChat(activeChat);

        return new ChatInfo(activeChat.getId());
    }

    @RolesAllowed({"ROLE_CONSULTANT", "ROLE_ANONYMOUS"})
    @RequestMapping(value = "/poll_for_messages", method = RequestMethod.POST)
    @ResponseBody
    public List<ChatMessageInfo> getLastMessages(@RequestBody(required = false) Long lastMessageId,
                                                 Principal principal,
                                                 HttpSession session) {
        Chat activeChat = null;
        if (principal != null) {
            activeChat = chatService.findActiveChatWithConsultant(userService.findByUsername(principal.getName()));
        } else {
            String chatSessionId = (String) session.getAttribute("chatSessionId");
            activeChat = chatService.findBySessionId(chatSessionId);
        }

        if (activeChat == null) {
            throw new RuntimeException("There is no active chat for this user");
        }

        ChatMessage lastMessage = null;
        if (lastMessageId != -1) {
            lastMessage = chatService.getChatMessageById(lastMessageId);
        }

        return getLastMessages(activeChat, lastMessage);
    }

    private List<ChatMessageInfo> getLastMessages(Chat chat, ChatMessage lastMessage) {
        List<ChatMessageInfo> chatMessageInfos = new ArrayList<>();

        List<ChatMessage> chatMessages = null;
        if (lastMessage != null) {
            chatMessages = chatService.getLastMessagesByChat(chat, lastMessage);
        } else {
            chatMessages = chatService.getLastMessagesByChat(chat);
        }

        for (ChatMessage chatMessage : chatMessages) {
            chatMessageInfos.add(ChatMessageInfo.of(chatMessage));
        }

        return chatMessageInfos;
    }

    @RolesAllowed({"ROLE_CONSULTANT", "ROLE_ANONYMOUS"})
    @RequestMapping(value = "/post_message", method = RequestMethod.POST)
    public ResponseEntity<String> postMessage(@RequestBody @Valid ChatMessageInfo chatMessageInfo,
                                  Principal principal,
                                  HttpSession session) {
        try {
            User consultant = null;
            Chat activeChat = null;
            if (principal == null) {
                // anonymous
                String chatSessionId = (String) session.getAttribute("chatSessionId");
                activeChat = chatService.findBySessionId(chatSessionId);
                if (activeChat == null) {
                    throw new RuntimeException("There is no active chat for this user");
                }
            } else {
                // consultant
                consultant = userService.findByUsername(principal.getName());
                if (consultant == null) {
                    throw new RuntimeException("Consultant not found");
                }
                activeChat = chatService.findActiveChatWithConsultant(consultant);
            }

            ChatMessage chatMessage = new ChatMessage(chatMessageInfo.getBody(),
                    LocalDateTime.now(DateTimeZone.UTC),
                    activeChat,
                    consultant);
            chatService.postNewMessage(chatMessage);
            return new ResponseEntity<>("", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
