package net.onlineconsultations.dao.impl;

import net.onlineconsultations.dao.UserDAO;
import net.onlineconsultations.domain.User;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateUserDAOImpl extends HibernateBaseDAO<User> implements UserDAO {
    public HibernateUserDAOImpl() {
        super(User.class);
    }

    public User findByUsername(String username) {
        return (User) sessionFactory.openSession()
                .createCriteria(User.class)
                .add(Restrictions.eq("username", username))
                .uniqueResult();
    }
}
