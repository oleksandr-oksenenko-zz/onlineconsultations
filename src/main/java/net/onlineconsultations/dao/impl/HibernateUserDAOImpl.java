package net.onlineconsultations.dao.impl;

import net.onlineconsultations.dao.UserDAO;
import net.onlineconsultations.domain.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class HibernateUserDAOImpl extends HibernateBaseDAO<User> implements UserDAO {
    @PersistenceContext
    private EntityManager em;

    public HibernateUserDAOImpl() {
        super(User.class);
    }

    public User findByUsername(String username) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> user = cq.from(User.class);
        cq.where(
                cb.equal(user.get("username"), username)
        );

        return em.createQuery(cq).getSingleResult();
    }
}
