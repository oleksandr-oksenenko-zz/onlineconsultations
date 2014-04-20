package net.onlineconsultations.dao;

import net.onlineconsultations.domain.SubSubject;
import net.onlineconsultations.domain.User;

import java.util.List;

public interface SubSubjectDAO extends GenericDao<SubSubject> {

    List<SubSubject> getSubSubjectsByUser(User user);

    List<SubSubject> getSubSubjectsBySubjectId(Long subjectId);

}
