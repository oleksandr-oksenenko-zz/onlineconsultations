package net.onlineconsultations.service;

import net.onlineconsultations.domain.Consultant;
import net.onlineconsultations.domain.SubSubject;
import net.onlineconsultations.domain.Subject;

import java.util.List;
import java.util.Map;

public interface ConsultantService {
    void recalculateConsultantRating(Consultant consultant, double newMark);

    void linkSubSubjectToConsultant(Consultant consultant, SubSubject newSubSubject);

    void unlinkSubSubjectFromConsultant(Consultant consultant, SubSubject subSubject);

    void changeConsultantStatus(Consultant consultant, boolean isWaitingForChat);

    Map<SubSubject, List<Consultant>> getAvailableConsultants(Subject subject);

    Consultant getByUsername(String username);

    Consultant getById(Long id);

    void remove(Long consultantId);

    void merge(Consultant consultant);
}
