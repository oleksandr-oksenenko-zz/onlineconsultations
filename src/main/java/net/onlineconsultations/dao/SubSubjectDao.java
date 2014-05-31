package net.onlineconsultations.dao;

import net.onlineconsultations.domain.Consultant;
import net.onlineconsultations.domain.SubSubject;

import java.util.List;

public interface SubSubjectDao extends GenericDao<SubSubject> {

    List<SubSubject> getByConsultant(Consultant consultant);

    List<SubSubject> getBySubjectId(Long subjectId);

}
