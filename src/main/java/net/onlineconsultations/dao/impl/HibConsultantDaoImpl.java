package net.onlineconsultations.dao.impl;

import net.onlineconsultations.dao.ConsultantDao;
import net.onlineconsultations.domain.Consultant;
import net.onlineconsultations.domain.SubSubject;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HibConsultantDaoImpl extends HibGenericDaoImpl<Consultant> implements ConsultantDao {
    public HibConsultantDaoImpl() {
        super(Consultant.class);
    }

    @Override
    public List<Consultant> getAvailableConsultants(SubSubject subSubject) {
        return em.createQuery("select c " +
                        " from Consultant c " +
                        " inner join c.subSubjects ss " +
                        " where ss.id = :subSubjectId " +
                        "  and c.waitingForChat = true" +
                        " order by c.rating",
                Consultant.class)
                .setParameter("subSubjectId", subSubject.getId())
                .getResultList();
    }

    @Override
    public Consultant getByUsername(String username) {
        return em.createQuery("select c " +
                " from Consultant c " +
                " where c.username = :username",
                Consultant.class)
                .setParameter("username", username)
                .getSingleResult();
    }
}
