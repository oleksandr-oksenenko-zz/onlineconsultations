package net.onlineconsultations.dao.impl;

import java.util.List;

import net.onlineconsultations.dao.ChatMessageDAO;
import net.onlineconsultations.domain.ChatMessage;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateChatMessageDAOImpl extends HibernateBaseDAO implements
	ChatMessageDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<ChatMessage> getAll() {
	return getMessages(null);
    }

    @Override
    public ChatMessage getById(Long id) {
	return (ChatMessage) getSession().get(ChatMessage.class, id);
    }

    @Override
    public void save(ChatMessage chatMessage) {
	getSession().save(chatMessage);
    }

    @Override
    public List<ChatMessage> getMessages(ChatMessage lastMessage) {
	Criteria criteria = getSession().createCriteria(ChatMessage.class);
	if (lastMessage == null) {
	    return criteria.list();
	} else {
	    return criteria.add(Restrictions.eq("chat", lastMessage.getChat()))
		    .add(Restrictions.ge("id", lastMessage.getId())).list();
	}
    }
}
