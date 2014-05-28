package net.onlineconsultations.service;

import net.onlineconsultations.domain.Consultant;
import net.onlineconsultations.domain.SubSubject;

import java.util.List;

public interface SubSubjectService {

    SubSubject getById(Long id);

    List<SubSubject> getAll();

    List<SubSubject> getByConsultant(Consultant consultant);

    List<SubSubject> getBySubjectId(Long subjectId);

    void remove(Long subSubjectId);

    void save(SubSubject subSubject);

    void merge(SubSubject subSubject);
}
