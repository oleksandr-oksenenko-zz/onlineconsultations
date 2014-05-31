package net.onlineconsultations.service.impl;

import net.onlineconsultations.dao.ChatDao;
import net.onlineconsultations.dao.ChatMessageDao;
import net.onlineconsultations.domain.*;
import net.onlineconsultations.service.ChatService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {
    @Inject
    private ChatDao chatDao;

    @Inject
    private ChatMessageDao chatMessageDao;

    @Override
    @Transactional
    public Chat startNewChat(Consultant consultantInChat) {
        String sessionId;
        do {
            sessionId = RandomStringUtils.randomAlphanumeric(32);
        } while (chatDao.findBySessionId(sessionId) != null);

        Chat newChat = new Chat(sessionId, consultantInChat, true);
        chatDao.save(newChat);
        return newChat;
    }

    @Override
    @Transactional
    public void endChatForAnonym(Chat chat) {
        chat.setAnonymInChat(false);
        if (!chat.isConsultantInChat()) {
            chat.setStatus(ChatStatus.CLOSED);
        }
        chatDao.merge(chat);
    }

    @Override
    @Transactional
    public void endChatForConsultant(Chat chat, Consultant consultant) {
        chat.setConsultantInChat(false);
        if (!chat.isAnonymInChat()) {
            chat.setStatus(ChatStatus.CLOSED);
        }
        chatDao.merge(chat);
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
        chatMessageDao.save(chatMessage);
    }

    @Override
    @Transactional
    public void setConsultantInChat(Chat chat) {
        chat.setConsultantInChat(true);
        chatDao.merge(chat);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ChatMessage> getLastMessagesInChat(Chat chat, ChatMessage lastMessage) {
        return chatMessageDao.getLastMessagesInChat(chat, lastMessage);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ChatMessage> getLastMessagesInChat(Chat chat, Long lastMessageId) {
        return chatMessageDao.getLastMessagesInChat(chat, lastMessageId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ChatMessage> getMessagesInChat(Chat chat) {
        return chatMessageDao.getMessagesInChat(chat);
    }

    @Override
    @Transactional(readOnly = true)
    public Chat findActiveChatWithConsultant(Consultant consultant) {
        return chatDao.findActiveChatWithConsultant(consultant);
    }

    @Override
    public Chat findBySessionId(String sessionId) {
        return chatDao.findBySessionId(sessionId);
    }
}
