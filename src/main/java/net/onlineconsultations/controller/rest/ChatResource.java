package net.onlineconsultations.controller.rest;

import net.onlineconsultations.controller.rest.model.ChatMessageInfo;
import net.onlineconsultations.domain.Chat;
import net.onlineconsultations.domain.ChatMessage;
import net.onlineconsultations.domain.User;
import net.onlineconsultations.service.ChatService;
import net.onlineconsultations.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
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

    @PreAuthorize("ROLE_CONSULTANT")
    @RequestMapping(value = "/poll_for_chat", method = RequestMethod.POST)
    @ResponseBody
    public Long consultantPollForChat(Principal principal) {
        User consultant = userService.findByUsername(principal.getName());

        Chat activeChat = chatService.getActiveChatWithConsultant(consultant);
        if (activeChat != null) {
            activeChat.setConsultantInChat(true);
            chatService.update(activeChat);

            return activeChat.getId();
        } else {
            return null;
        }
    }

    @PreAuthorize("ROLE_CONSULTANT")
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
}
