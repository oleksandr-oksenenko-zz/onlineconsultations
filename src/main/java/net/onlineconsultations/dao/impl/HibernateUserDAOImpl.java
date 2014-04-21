package net.onlineconsultations.dao.impl;

import net.onlineconsultations.dao.UserDAO;
import net.onlineconsultations.domain.SubSubject;
import net.onlineconsultations.domain.User;
import net.onlineconsultations.domain.UserRole;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

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

    @Override
    public List<User> getOnlineConsultantsBySubSubject(SubSubject subSubject) {
        return em.createQuery("select u " +
                        " from User u " +
                        " inner join u.subSubjects ss " +
                        " where u.role = :consultantRole " +
                        "  and ss.id = :subSubjectId " +
                        "  and u.waitingForChat = true",
                User.class)
                .setParameter("consultantRole", UserRole.ROLE_CONSULTANT)
                .setParameter("subSubjectId", subSubject.getId())
                .getResultList();
    }
}
