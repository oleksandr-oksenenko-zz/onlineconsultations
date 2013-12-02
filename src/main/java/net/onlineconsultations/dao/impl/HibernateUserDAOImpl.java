package net.onlineconsultations.dao.impl;

import java.util.List;

import net.onlineconsultations.dao.UserDAO;
import net.onlineconsultations.domain.User;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateUserDAOImpl extends HibernateBaseDAO implements UserDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public List<User> getAll() {
	return getSession().createCriteria(User.class).list();
    }

    public User getById(Long id) {
	return (User) getSession().get(User.class, id);
    }

    public User getByUsername(String username) {
	return (User) getSession().createCriteria(User.class)
		.add(Restrictions.eq("username", username)).uniqueResult();
    }

    public void save(User user) {
	getSession().save(user);
    }
}
