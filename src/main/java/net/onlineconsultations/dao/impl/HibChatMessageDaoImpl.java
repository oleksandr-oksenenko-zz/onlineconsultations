package net.onlineconsultations.dao.impl;

import net.onlineconsultations.dao.ChatMessageDao;
import net.onlineconsultations.domain.Chat;
import net.onlineconsultations.domain.ChatMessage;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HibChatMessageDaoImpl extends HibGenericDaoImpl<ChatMessage> implements ChatMessageDao {
    public HibChatMessageDaoImpl() {
        super(ChatMessage.class);
    }

    @Override
    public List<ChatMessage> getMessagesInChat(Chat chat) {
        return em.createQuery("select cm " +
                " from ChatMessage cm " +
                " where cm.chat = :chat",
                ChatMessage.class)
                .setParameter("chat", chat)
                .getResultList();
    }

    @Override
    public List<ChatMessage> getLastMessagesInChat(Chat chat, ChatMessage lastMessage) {
        return getLastMessagesInChat(chat, lastMessage.getId());
    }

    @Override
    public List<ChatMessage> getLastMessagesInChat(Chat chat, Long lastMessageId) {
        return em.createQuery("select cm " +
                " from ChatMessage cm " +
                " where cm.chat = :chat " +
                "  and cm.id > :lastMessageId",
                ChatMessage.class)
                .setParameter("chat", chat)
                .setParameter("lastMessageId", lastMessageId)
                .getResultList();
    }
}
