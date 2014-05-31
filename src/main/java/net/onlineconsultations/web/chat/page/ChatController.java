package net.onlineconsultations.web.chat.page;

import net.onlineconsultations.domain.Chat;
import net.onlineconsultations.domain.Consultant;
import net.onlineconsultations.service.ChatService;
import net.onlineconsultations.service.ConsultantService;
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
    private ConsultantService consultantService;

    @RolesAllowed({"ROLE_ANONYMOUS", "ROLE_CONSULTANT"})
    @RequestMapping(method = RequestMethod.GET)
    public String chatPage(Principal principal,
                           HttpSession session) {
        Chat activeChat;
        if (principal != null) {

            Consultant consultant = consultantService.getByUsername(principal.getName());
            activeChat = chatService.findActiveChatWithConsultant(consultant);

            if (!activeChat.isConsultantInChat()) {
                activeChat.setConsultantInChat(true);
            }

        } else {

            String chatSessionId = (String) session.getAttribute("chatSessionId");
            activeChat = chatService.findBySessionId(chatSessionId);

            if (!activeChat.isAnonymInChat()) {
                activeChat.setAnonymInChat(true);
            }

        }

        if (activeChat == null) {
            return "redirect:/";
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
        Consultant consultant = consultantService.getById(consultantId);
        Chat newChat = chatService.startNewChat(consultant);

        session.setAttribute("chatSessionId", newChat.getSessionId());

        return "redirect:/chat";
    }

    @RolesAllowed({ "ROLE_ANONYMOUS", "ROLE_CONSULTANT"})
    @RequestMapping(value = "/end", method = RequestMethod.GET)
    public String endChat(Principal principal,
                          HttpSession session) {
        if (principal != null) {
            Consultant consultant = consultantService.getByUsername(principal.getName());
            Chat activeChat = chatService.findActiveChatWithConsultant(consultant);

            if (activeChat == null) {
                throw new RuntimeException("No active chat for this consultant");
            }

            chatService.endChatForConsultant(activeChat, consultant);

            return "redirect:/";
        } else {
            String chatSessionId = (String) session.getAttribute("chatSessionId");
            if (chatSessionId == null) {
                throw new RuntimeException("Chat not found");
            }

            Chat chat = chatService.findBySessionId(chatSessionId);

            if (chat == null) {
                throw new RuntimeException("Chat with such a session id is not found");
            }

            chatService.endChatForAnonym(chat);

            Long consultantId = chat.getConsultantInChat().getId();
            return "redirect:/rate/" + consultantId;
        }
    }
}
