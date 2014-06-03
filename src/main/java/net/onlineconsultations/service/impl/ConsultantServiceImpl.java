package net.onlineconsultations.service.impl;

import net.onlineconsultations.dao.ConsultantDao;
import net.onlineconsultations.dao.SubjectDao;
import net.onlineconsultations.domain.Consultant;
import net.onlineconsultations.domain.SubSubject;
import net.onlineconsultations.domain.Subject;
import net.onlineconsultations.service.ConsultantService;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ConsultantServiceImpl implements ConsultantService {
    @Inject
    private ConsultantDao consultantDao;

    @Inject
    private SubjectDao subjectDao;

    @Inject
    private MessageDigestPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void recalculateConsultantRating(Consultant consultant, double newMark) {
        consultant.addToRaiting(newMark);
        consultantDao.merge(consultant);
    }

    @Override
    @Transactional
    public void linkSubSubjectToConsultant(Consultant consultant, SubSubject newSubSubject) {
        consultant.getSubSubjects().add(newSubSubject);
        consultantDao.merge(consultant);
    }

    @Override
    @Transactional
    public void unlinkSubSubjectFromConsultant(Consultant consultant, SubSubject subSubject) {
        consultant.getSubSubjects().remove(subSubject);
        consultantDao.merge(consultant);
    }

    @Override
    @Transactional
    public void changeConsultantStatus(Consultant consultant, boolean isWaitingForChat) {
        consultant.setWaitingForChat(isWaitingForChat);
        consultantDao.merge(consultant);
    }

    @Override
    @Transactional(readOnly = true)
    public Map<SubSubject, List<Consultant>> getAvailableConsultants(Subject subject) {
        Map<SubSubject, List<Consultant>> consultants = new HashMap<>();
        subject = subjectDao.getById(subject.getId());
        for (SubSubject subSubject : subject.getSubSubjects()) {
            List<Consultant> availableConsultants = consultantDao.getAvailableConsultants(subSubject);
            consultants.put(subSubject, availableConsultants);
        }
        return consultants;
    }

    @Override
    @Transactional(readOnly = true)
    public Consultant getByUsername(String username) {
        return consultantDao.getByUsername(username);
    }

    @Override
    @Transactional(readOnly = true)
    public Consultant getById(Long id) {
        return consultantDao.getById(id);
    }

    @Override
    @Transactional
    public void remove(Long consultantId) {
        Consultant consultant = consultantDao.getById(consultantId);
        for (SubSubject subSubject : consultant.getSubSubjects()) {
            subSubject.getSubSubjectConsultants().remove(consultant);
        }
        consultantDao.merge(consultant);
    }

    @Override
    @Transactional
    public void merge(Consultant consultant) {
        Consultant oldConsultant = consultantDao.getById(consultant.getId());

        if (!consultant.getPassword().isEmpty()) {
            String encodedPassword = passwordEncoder.encodePassword(consultant.getPassword(), null);

            consultant.setPassword(encodedPassword);
        } else {
            consultant.setPassword(oldConsultant.getPassword());
        }

        consultantDao.merge(consultant);
    }
}
