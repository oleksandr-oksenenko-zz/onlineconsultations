package net.onlineconsultations.service.impl;

import java.util.List;

import net.onlineconsultations.dao.ChatDAO;
import net.onlineconsultations.dao.ChatMessageDAO;
import net.onlineconsultations.domain.Chat;
import net.onlineconsultations.domain.ChatMessage;
import net.onlineconsultations.domain.ChatStatus;
import net.onlineconsultations.domain.User;
import net.onlineconsultations.service.ChatService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ChatServiceImpl implements ChatService {
    @Autowired
    private ChatDAO chatDAO;

    @Autowired
    private ChatMessageDAO chatMessageDAO;

    @Override
    public Chat startNewChat(User consultantInChat) {
        Chat newChat = new Chat(consultantInChat);
        newChat.setAnonymInChat(true);
        this.chatDAO.save(newChat);
        return newChat;
    }

    @Override
    public void endChat(Chat chat) {
        chat.setStatus(ChatStatus.CLOSED);
        this.chatDAO.update(chat);
    }

    @Override
    public void postNewMessage(ChatMessage chatMessage) {
        if (chatMessage.getChat() == null) {
            throw new RuntimeException("No chat object specified");
        }
        if (chatMessage.getChat().getStatus().equals(ChatStatus.CLOSED)) {
            throw new RuntimeException("Chat has been already closed");
        }
        this.chatMessageDAO.save(chatMessage);
    }

    @Override
    public List<ChatMessage> getMessages(Chat chat, ChatMessage lastMessage) {
        return this.chatMessageDAO.getMessages(chat, lastMessage);
    }

    @Override
    public Chat getChatById(Long id) {
        return this.chatDAO.getById(id);
    }

    @Override
    public Chat getActiveChatWithConsultant(User consultant) {
        return this.chatDAO.getActiveChatWithConsultant(consultant.getId());
    }

    @Override
    public void update(Chat chat) {
        this.chatDAO.update(chat);
    }

    @Override
    public ChatMessage getChatMessageById(Long id) {
        return this.chatMessageDAO.getById(id);
    }
}
