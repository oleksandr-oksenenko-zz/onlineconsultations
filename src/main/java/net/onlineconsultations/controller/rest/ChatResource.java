package net.onlineconsultations.controller.rest;

import net.onlineconsultations.controller.rest.model.ChatMessageInfo;
import net.onlineconsultations.dao.exception.NoSuchRecordException;
import net.onlineconsultations.domain.Chat;
import net.onlineconsultations.domain.ChatMessage;
import net.onlineconsultations.domain.User;
import net.onlineconsultations.service.ChatService;
import net.onlineconsultations.service.UserService;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/chat")
public class ChatResource {
    @Inject
    private ChatService chatService;

    @Inject
    private UserService userService;

    @RolesAllowed("ROLE_CONSULTANT")
    @RequestMapping(value = "/poll_for_chat", method = RequestMethod.POST)
    @ResponseBody
    public Long consultantPollForChat(Principal principal) {
        User consultant = userService.findByUsername(principal.getName());

        Chat activeChat;
        try {
            activeChat = chatService.getActiveChatWithConsultant(consultant);
            chatService.setConsultantInChat(activeChat);

            return activeChat.getId();
        } catch (NoSuchRecordException e) {
            return null;
        }
    }

    @RolesAllowed({"ROLE_CONSULTANT", "ROLE_ANONYMOUS"})
    @RequestMapping(value = "/poll_for_messages", method = RequestMethod.POST)
    @ResponseBody
    public List<ChatMessageInfo> getLastMessages(Principal principal) {
        Chat chat = chatService.getActiveChatWithConsultant(
                userService.findByUsername(principal.getName()));

        List<ChatMessageInfo> chatMessageInfos = new ArrayList<>();
        for (ChatMessage chatMessage : chatService.getLastMessagesByChat(chat, null)) {
            chatMessageInfos.add(ChatMessageInfo.of(chatMessage));
        }

        return chatMessageInfos;
    }

    @RolesAllowed({"ROLE_CONSULTANT", "ROLE_ANONYMOUS"})
    @RequestMapping(value = "/post_message", method = RequestMethod.POST, params = { "message" })
    public HttpStatus postMessage(@RequestParam("message") ChatMessageInfo chatMessageInfo,
                                  Principal principal,
                                  HttpSession session) {
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
            activeChat = chatService.getActiveChatWithConsultant(consultant);
        }

        ChatMessage chatMessage = new ChatMessage(chatMessageInfo.getBody(),
                LocalDateTime.now(DateTimeZone.UTC),
                activeChat,
                consultant);
        chatService.postNewMessage(chatMessage);

        return HttpStatus.OK;
    }
}
