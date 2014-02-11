package net.onlineconsultations.dao.impl;

import net.onlineconsultations.dao.SubSubjectDAO;
import net.onlineconsultations.domain.SubSubject;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateSubSubjectDAOImpl extends HibernateBaseDAO<SubSubject>
        implements SubSubjectDAO {

    public HibernateSubSubjectDAOImpl() {
        super(SubSubject.class);
    }
}
