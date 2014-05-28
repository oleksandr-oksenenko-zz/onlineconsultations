package net.onlineconsultations.dao;

import net.onlineconsultations.domain.Consultant;
import net.onlineconsultations.domain.SubSubject;

import java.util.List;

public interface ConsultantDao extends GenericDao<Consultant> {
    List<Consultant> getAvailableConsultants(SubSubject subSubject);

    Consultant getByUsername(String username);
}
