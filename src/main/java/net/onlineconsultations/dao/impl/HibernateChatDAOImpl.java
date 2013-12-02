package net.onlineconsultations.dao.impl;

import java.util.List;

import net.onlineconsultations.dao.ChatDAO;
import net.onlineconsultations.domain.Chat;
import net.onlineconsultations.domain.ChatStatus;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateChatDAOImpl extends HibernateBaseDAO implements ChatDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Chat> getAll() {
	return getSession().createCriteria(Chat.class).list();
    }

    @Override
    public Chat getById(Long id) {
	return (Chat) getSession().get(Chat.class, id);
    }

    @Override
    public void save(Chat chat) {
	getSession().save(chat);
    }

    @Override
    public void update(Chat chat) {
	getSession().update(chat);
    }

    @Override
    public Chat getActiveChatWithConsultant(Long consultantId) {
	return (Chat) getSession().createCriteria(Chat.class)
		.add(Restrictions.eq("status", ChatStatus.ACTIVE))
		.add(Restrictions.eq("consultantInChat.id", consultantId))
		.uniqueResult();
    }
}
