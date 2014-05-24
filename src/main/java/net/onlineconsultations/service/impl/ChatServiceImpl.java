package net.onlineconsultations.service.impl;

import net.onlineconsultations.dao.ChatDAO;
import net.onlineconsultations.dao.ChatMessageDAO;
import net.onlineconsultations.domain.Chat;
import net.onlineconsultations.domain.ChatMessage;
import net.onlineconsultations.domain.ChatStatus;
import net.onlineconsultations.domain.User;
import net.onlineconsultations.service.ChatService;
import org.apache.commons.lang.RandomStringUtils;
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
    public Chat startNewChat(User consultantInChat) {
        String sessionId;
        do {
            sessionId = RandomStringUtils.randomAlphanumeric(32);
        } while (chatDAO.findBySessionId(sessionId) != null);

        Chat newChat = new Chat(sessionId, consultantInChat, true);
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
    public void setConsultantInChat(Chat chat) {
        chat.setConsultantInChat(true);
    }

    @Override
    public List<ChatMessage> getLastMessagesByChat(Chat chat, ChatMessage lastMessage) {
        return chatMessageDAO.getLastMessagesByChat(chat, lastMessage);
    }

    @Override
    public List<ChatMessage> getLastMessagesByChat(Chat chat) {
        return chatMessageDAO.getLastMessagesByChat(chat);
    }

    @Override
    public Chat findActiveChatWithConsultant(User consultant) {
        return chatDAO.findActiveChatWithConsultant(consultant.getId());
    }

    @Override
    public ChatMessage getChatMessageById(Long id) {
        return chatMessageDAO.getById(id);
    }

    @Override
    public Chat findBySessionId(String sessionId) {
        return chatDAO.findBySessionId(sessionId);
    }
}
