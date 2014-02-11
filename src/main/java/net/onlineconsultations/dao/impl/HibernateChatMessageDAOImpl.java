package net.onlineconsultations.dao.impl;

import net.onlineconsultations.dao.ChatMessageDAO;
import net.onlineconsultations.domain.Chat;
import net.onlineconsultations.domain.ChatMessage;
import org.hibernate.Criteria;
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
    public List<ChatMessage> getLastMessagesByChat(Chat chat, ChatMessage lastMessage) {
        Criteria criteria = sessionFactory.openSession()
                .createCriteria(ChatMessage.class).add(Restrictions.eq("chat", chat));
        if (lastMessage == null) {
            return criteria.list();
        } else {
            return criteria.add(Restrictions.gt("id", lastMessage.getId()))
                    .list();
        }
    }
}
