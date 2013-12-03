package net.onlineconsultations.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import net.onlineconsultations.controller.dto.ChatMessageDTO;
import net.onlineconsultations.domain.Chat;
import net.onlineconsultations.domain.ChatMessage;
import net.onlineconsultations.domain.User;
import net.onlineconsultations.service.ChatService;
import net.onlineconsultations.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@Scope("session")
@RequestMapping(value = "/chat")
public class ChatController {
    private ObjectMapper objectMapper = new ObjectMapper();

    private Chat chat;

    private User consultant;

    @Autowired
    private ChatService chatService;

    @Autowired
    private UserService userService;

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
        if (action.equals("start")) {
            if (this.chat == null) {
                this.chat = this.chatService.startNewChat(this.userService
                        .getById(consultantId));
            } else {
                throw new RuntimeException("Chat has been already started");
            }
        }

        if (principal != null) {
            redirectAttributes.addFlashAttribute("consultant", principal);
        }

        return "redirect:/chat";
    }

    @RequestMapping(method = RequestMethod.GET, params = { "chat" })
    public String chatPage(@RequestParam("chat") Long chatId,
            Principal principal, RedirectAttributes redirectAttributes) {
        chat = this.chatService.getChatById(chatId);
        consultant = chat.getConsultantInChat();

        if (principal != null) {
            redirectAttributes.addFlashAttribute("consultant", principal);
        }

        return "redirect:/chat";
    }

    @RequestMapping(method = RequestMethod.POST, params = { "action", "message" })
    public ResponseEntity<String> ajaxPostMessage(
            @RequestParam("action") String action,
            @RequestParam("message") String message, Principal principal)
                    throws JsonProcessingException {

        ChatMessage chatMessage = null;
        if (principal != null) {
            chatMessage = new ChatMessage(message, Calendar.getInstance(),
                    this.chat, this.userService.getByUsername(principal
                            .getName()));
        } else {
            chatMessage = new ChatMessage(message, Calendar.getInstance(),
                    this.chat, null);
        }

        this.chatService.postNewMessage(chatMessage);

        return new ResponseEntity<>("[]", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, params = "action")
    public ResponseEntity<String> ajaxPollForConsultant(
            @RequestParam("action") String action,
            Principal principal)
                    throws JsonProcessingException {

        if (action.equals("pollForConsultant")) {
            chat = chatService.getChatById(chat.getId());

            return new ResponseEntity<String>(
                    objectMapper.writeValueAsString(chat.isConsultantInChat()),
                    HttpStatus.OK);
        } else if (action.equals("endChat")) {
            if (this.chat != null) {
                chat = chatService.getChatById(chat.getId());

                if (principal != null) {
                    chat.setConsultantInChat(false);
                } else {
                    chat.setAnonymInChat(false);
                }
                this.chatService.endChat(chat);

                chat = null;
                consultant = null;

                return new ResponseEntity<>("[]", HttpStatus.OK);
            } else {
                throw new RuntimeException("Chat has not been started yet");
            }
        } else {
            return new ResponseEntity<>("[]", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.POST, params = { "action",
    "lastMessage" })
    @ResponseBody
    public ResponseEntity<String> ajaxPollForMessages(
            @RequestParam("action") String action,
            @RequestParam(value = "lastMessage") Long lastMessageId)
                    throws JsonProcessingException {
        if (action.equals("pollForMessages")) {
            List<ChatMessage> lastMessages = null;
            if (lastMessageId != -1) {
                lastMessages = this.chatService.getMessages(chat,
                        this.chatService.getChatMessageById(lastMessageId));
            } else {
                lastMessages = this.chatService.getMessages(chat, null);
            }

            List<ChatMessageDTO> dtos = new ArrayList<>(lastMessages.size());
            for (ChatMessage chatMessage : lastMessages) {
                dtos.add(new ChatMessageDTO(chatMessage));
            }

            return new ResponseEntity<String>(
                    objectMapper.writeValueAsString(dtos),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Wrong action",
                    HttpStatus.BAD_REQUEST);
        }
    }
}
