package net.onlineconsultations.dao.impl;

import java.util.List;

import net.onlineconsultations.dao.ChatMessageDAO;
import net.onlineconsultations.domain.ChatMessage;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateChatMessageDAOImpl implements ChatMessageDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<ChatMessage> getAll() {
        return sessionFactory.openSession().createCriteria(ChatMessage.class)
                .list();
    }

    @Override
    public ChatMessage getById(Long id) {
        return (ChatMessage) sessionFactory.openSession().get(
                ChatMessage.class, id);
    }

    @Override
    public void save(ChatMessage chatMessage) {
        sessionFactory.openSession().save(chatMessage);
    }

}
