package net.onlineconsultations.controller;

import net.onlineconsultations.controller.restmodel.ChatMessage;
import net.onlineconsultations.domain.Chat;
import net.onlineconsultations.domain.User;
import net.onlineconsultations.service.ChatService;
import net.onlineconsultations.service.UserService;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@Scope("session")
@RequestMapping(value = "/chat")
public class ChatController {
    @Autowired
    private ChatService chatService;

    @Autowired
    private UserService userService;

    /* session scoped objects */
    private Chat chat;

    private User consultant;

    @RequestMapping(method = RequestMethod.GET)
    public String chatPage() {
        if (chat == null) {
            throw new RuntimeException("You should start a chat first");
        }
        return "chat";
    }

    @RequestMapping(method = RequestMethod.GET, params = { "action" })
    public String chatPage(@RequestParam("action") String action,
                           @RequestParam(value = "consultant", required = false) Long consultantId,
                           Principal principal,
                           RedirectAttributes redirectAttributes) {

        if (!action.equals("startChat")) {
            throw new RuntimeException("Unsupported action");
        }
        if (chat != null) {
            throw new RuntimeException("Chat has been already started");
        }

        if (principal != null) {

            consultant = userService.getByUsername(principal.getName());
            chat = chatService.getActiveChatWithConsultant(consultant);
            redirectAttributes.addFlashAttribute("consultant", principal);

        } else {

            chat = chatService.startChat(userService.getById(consultantId));
            consultant = userService.getById(consultantId);

        }

        return "redirect:/chat";
    }

    @RequestMapping(method = RequestMethod.POST, params = { "pollForMessages", "lastMessage" })
    @ResponseBody
    public List<ChatMessage> pollForMessages(@RequestParam("lastMessage") Long lastMessageId) {

        List<net.onlineconsultations.domain.ChatMessage> lastMessages;
        if (lastMessageId != -1) {

            lastMessages = chatService.getMessages(chat, chatService.getChatMessageById(lastMessageId));

        } else {

            lastMessages = this.chatService.getMessages(chat, null);

        }

        List<ChatMessage> response = new ArrayList<>(lastMessages.size());
        for (net.onlineconsultations.domain.ChatMessage chatMessage : lastMessages) {
            response.add(new ChatMessage(chatMessage));
        }

        return response;
    }

    @RequestMapping(method = RequestMethod.POST, params = { "pollForConsultant" })
    @ResponseBody
    public Boolean pollForConsultant() {
        chat = chatService.getChatById(chat.getId());

        return chat.isConsultantInChat();
    }

    @RequestMapping(method = RequestMethod.POST, params = { "postMessage", "message" })
    public HttpStatus postMessage(@RequestParam("message") String message,
                                  Principal principal) {

        net.onlineconsultations.domain.ChatMessage chatMessage;
        if (principal != null) {
            chatMessage = new net.onlineconsultations.domain.ChatMessage(message,
                    LocalDateTime.now(DateTimeZone.UTC),
                    chat,
                    userService.getByUsername(principal.getName()));
        } else {
            chatMessage = new net.onlineconsultations.domain.ChatMessage(message,
                    LocalDateTime.now(DateTimeZone.UTC),
                    chat,
                    null);
        }
        chatService.postNewMessage(chatMessage);

        return HttpStatus.OK;
    }

    @RequestMapping(method = RequestMethod.POST, params = { "endChat" })
    public HttpStatus endChat(Principal principal) {
        if (chat == null) {
            throw new RuntimeException("Chat has not been started yet");
        }

        if (principal != null) {
            chat.setConsultantInChat(false);
        } else {
            chat.setAnonymInChat(false);
        }
        chatService.update(chat);

        if (!chat.isAnonymInChat() && !chat.isConsultantInChat()) {
            chatService.endChat(chat);
        }

        chat = null;
        consultant = null;

        return HttpStatus.OK;
    }
}
