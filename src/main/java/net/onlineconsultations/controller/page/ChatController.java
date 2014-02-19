package net.onlineconsultations.controller.page;

import net.onlineconsultations.domain.Chat;
import net.onlineconsultations.domain.ChatStatus;
import net.onlineconsultations.domain.User;
import net.onlineconsultations.service.ChatService;
import net.onlineconsultations.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
@RequestMapping(value = "/chat")
public class ChatController {
    @Inject
    private ChatService chatService;

    @Inject
    private UserService userService;

    @RolesAllowed({"ROLE_ANONYMOUS", "ROLE_CONSULTANT"})
    @RequestMapping(method = RequestMethod.GET)
    public String chatPage(Principal principal,
                           HttpSession session) {
        if (principal != null) {
            User consultant = userService.findByUsername(principal.getName());
            Chat activeChat = chatService.findActiveChatWithConsultant(consultant);
            if (activeChat == null) {
                return "redirect:/consultant";
            }
        } else {
            String chatSessionId = (String) session.getAttribute("chatSessionId");
            Chat activeChat = chatService.findBySessionId(chatSessionId);
            if (activeChat == null) {
                return "redirect:/";
            }
        }
        return "chat";
    }

    /**
     * Handler for start chat action for anonymous users.
     * */
    @RolesAllowed("ROLE_ANONYMOUS")
    @RequestMapping(value = "/start/{consultant}", method = RequestMethod.GET)
    public String startChat(@PathVariable("consultant") Long consultantId,
                          HttpSession session) {
        User consultant = userService.getById(consultantId);
        Chat newChat = chatService.startNewChat(consultant);

        session.setAttribute("chatSessionId", newChat.getSessionId());

        return "redirect:/chat";
    }

    @RolesAllowed("ROLE_CONSULTANT")
    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public String startChat(Principal principal) {
        User consultant = userService.findByUsername(principal.getName());

        if (consultant == null) {
            // TODO: fix this with appropriate exception type
            throw new RuntimeException("Consultant not found");
        }

        Chat chat = chatService.findActiveChatWithConsultant(consultant);
        if (chat == null) {
            throw new RuntimeException("No active chat found with " + consultant + " consultant");
        }
        chat.setConsultantInChat(true);

        return "redirect:/chat";
    }

    @RolesAllowed("ROLE_ANONYMOUS")
    @RequestMapping(value = "/end", method = RequestMethod.GET)
    public String endChat(HttpSession session) {
        String chatSessionId = (String) session.getAttribute("chatSessionId");
        if (chatSessionId == null) {
            // TODO: fix this with appropriate exception type
            throw new RuntimeException("Chat not found");
        }

        Chat chat = chatService.findBySessionId(chatSessionId);
        if (chat.getStatus() == ChatStatus.CLOSED) {
            // TODO: fix this with appropriate exception type
            throw new RuntimeException("Chat already closed");
        }
        chatService.endChat(chat);

        return "redirect:/";
    }
}
