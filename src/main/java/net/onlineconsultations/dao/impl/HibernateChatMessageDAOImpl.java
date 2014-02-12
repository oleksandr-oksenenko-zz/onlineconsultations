package net.onlineconsultations.dao.impl;

import net.onlineconsultations.dao.ChatMessageDAO;
import net.onlineconsultations.domain.Chat;
import net.onlineconsultations.domain.ChatMessage;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HibernateChatMessageDAOImpl extends HibernateBaseDAO<ChatMessage>
        implements ChatMessageDAO {

    public HibernateChatMessageDAOImpl() {
        super(ChatMessage.class);
    }

    @Override
    public List<ChatMessage> getLastMessagesByChat(Chat chat) {
        return (List<ChatMessage>) sessionFactory.openSession()
                .createCriteria(Chat.class)
                .add(Restrictions.eq("chat", chat))
                .list();
    }

    @Override
    public List<ChatMessage> getLastMessagesByChat(Chat chat, ChatMessage lastMessage) {
        return (List<ChatMessage>) sessionFactory.openSession()
                .createCriteria(ChatMessage.class).add(Restrictions.eq("chat", chat))
                .add(Restrictions.gt("id", lastMessage.getId()))
                .list();
    }
}
