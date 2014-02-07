package net.onlineconsultations.controller;

import net.onlineconsultations.domain.Chat;
import net.onlineconsultations.domain.User;
import net.onlineconsultations.service.ChatService;
import net.onlineconsultations.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
@RequestMapping("/consultant")
public class ConsultantController {
    @Autowired
    private ChatService chatService;

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String consultantPage() {
        return "consultant";
    }

    @RequestMapping(method = RequestMethod.POST, params = { "pollForChat" })
    public @ResponseBody Long pollForChat(Principal principal) {
        User consultant = userService.getByUsername(principal.getName());

        Chat chat = chatService.getActiveChatWithConsultant(consultant);

        if (chat != null) {
            chat.setConsultantInChat(true);
            chatService.update(chat);
            return chat.getId();
        } else {
            return null;
        }
    }
}
