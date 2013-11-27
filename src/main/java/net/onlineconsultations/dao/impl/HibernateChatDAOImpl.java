package net.onlineconsultations.dao.impl;

import java.util.List;

import net.onlineconsultations.dao.ChatDAO;
import net.onlineconsultations.domain.Chat;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateChatDAOImpl implements ChatDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Chat> getAll() {
        return sessionFactory.openSession().createCriteria(Chat.class).list();
    }

    @Override
    public Chat getById(Long id) {
        return (Chat) sessionFactory.openSession().get(Chat.class, id);
    }

    @Override
    public void save(Chat chat) {
        sessionFactory.openSession().save(chat);
    }

}
