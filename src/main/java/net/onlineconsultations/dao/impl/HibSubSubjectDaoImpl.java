package net.onlineconsultations.dao.impl;

import net.onlineconsultations.dao.SubSubjectDao;
import net.onlineconsultations.domain.Consultant;
import net.onlineconsultations.domain.SubSubject;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HibSubSubjectDaoImpl extends HibGenericDaoImpl<SubSubject> implements SubSubjectDao {
    public HibSubSubjectDaoImpl() {
        super(SubSubject.class);
    }

    @Override
    public List<SubSubject> getByConsultant(Consultant consultant) {
        return em.createQuery("select ss " +
                " from SubSubject ss " +
                " inner join ss.subSubjectConsultants users" +
                " where users.id = :userId",
                SubSubject.class)
                .setParameter("userId", consultant.getId())
                .getResultList();
    }

    @Override
    public List<SubSubject> getBySubjectId(Long subjectId) {
        return em.createQuery("select ss " +
                " from SubSubject ss " +
                " where ss.parentSubject.id = :subjectId",
                SubSubject.class)
                .setParameter("subjectId", subjectId)
                .getResultList();
    }
}
