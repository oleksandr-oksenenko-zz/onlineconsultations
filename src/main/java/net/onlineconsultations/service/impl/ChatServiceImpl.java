package net.onlineconsultations.service.impl;

import net.onlineconsultations.dao.ChatDAO;
import net.onlineconsultations.dao.ChatMessageDAO;
import net.onlineconsultations.domain.Chat;
import net.onlineconsultations.domain.ChatMessage;
import net.onlineconsultations.domain.ChatStatus;
import net.onlineconsultations.domain.User;
import net.onlineconsultations.service.ChatService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {
    @Inject
    private ChatDAO chatDAO;

    @Inject
    private ChatMessageDAO chatMessageDAO;

    @Override
    @Transactional
    public Chat startChat(User consultantInChat) {
        Chat newChat = new Chat(consultantInChat);
        newChat.setAnonymInChat(true);
        chatDAO.save(newChat);
        return newChat;
    }

    @Override
    @Transactional
    public void endChat(Chat chat) {
        chat.setStatus(ChatStatus.CLOSED);
        chatDAO.merge(chat);
    }

    @Override
    @Transactional
    public void postNewMessage(ChatMessage chatMessage) {
        if (chatMessage.getChat() == null) {
            throw new RuntimeException("No chat object specified");
        }
        if (chatMessage.getChat().getStatus().equals(ChatStatus.CLOSED)) {
            throw new RuntimeException("Chat has been already closed");
        }
        chatMessageDAO.save(chatMessage);
    }

    @Override
    public List<ChatMessage> getLastMessagesByChat(Chat chat, ChatMessage lastMessage) {
        return chatMessageDAO.getLastMessagesByChat(chat, lastMessage);
    }

    @Override
    public Chat getChatById(Long id) {
        return chatDAO.getById(id);
    }

    @Override
    public Chat getActiveChatWithConsultant(User consultant) {
        return chatDAO.getActiveChatWithConsultant(consultant.getId());
    }

    @Override
    @Transactional
    public void update(Chat chat) {
        chatDAO.merge(chat);
    }

    @Override
    public ChatMessage getChatMessageById(Long id) {
        return chatMessageDAO.getById(id);
    }
}
