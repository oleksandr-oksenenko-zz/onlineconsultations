package net.onlineconsultations.controller;

import java.security.Principal;

import net.onlineconsultations.domain.Chat;
import net.onlineconsultations.domain.User;
import net.onlineconsultations.service.ChatService;
import net.onlineconsultations.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/consultant")
public class ConsultantController {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private ChatService chatService;

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String consultantPage() {
        return "consultant";
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    String ajaxGetChatWithConsultant(Principal principal, HttpStatus status)
            throws JsonProcessingException {
        User consultant = this.userService.getByUsername(principal.getName());

        Chat chat = this.chatService.getActiveChatWithConsultant(consultant);

        if (chat != null) {
            chat.setConsultantInChat(true);
            this.chatService.update(chat);
            return objectMapper.writeValueAsString(chat.getId());
        } else {
            return objectMapper.writeValueAsString(null);
        }
    }
}
