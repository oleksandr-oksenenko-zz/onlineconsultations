package net.onlineconsultations.dao.impl;

import net.onlineconsultations.dao.SubSubjectDAO;
import net.onlineconsultations.domain.SubSubject;
import net.onlineconsultations.domain.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class HibernateSubSubjectDAOImpl extends HibernateBaseDAO<SubSubject> implements SubSubjectDAO {
    @PersistenceContext
    private EntityManager em;

    public HibernateSubSubjectDAOImpl() {
        super(SubSubject.class);
    }

    @Override
    public List<SubSubject> getSubSubjectsByUser(User user) {
        return em.createQuery("select ss " +
                " from SubSubject ss " +
                " inner join ss.subSubjectUsers users" +
                " where users.id = :userId",
                SubSubject.class)
                .setParameter("userId", user.getId())
                .getResultList();
    }

    @Override
    public List<SubSubject> getSubSubjectsBySubjectId(Long subjectId) {
        return em.createQuery("select ss " +
                " from SubSubject ss " +
                " where ss.parentSubject.id = :subjectId",
                SubSubject.class)
                .setParameter("subjectId", subjectId)
                .getResultList();
    }
}
