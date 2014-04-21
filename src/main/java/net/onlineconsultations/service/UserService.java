package net.onlineconsultations.service;

import net.onlineconsultations.domain.SubSubject;
import net.onlineconsultations.domain.Subject;
import net.onlineconsultations.domain.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    User getById(Long id);

    List<User> getAllUsers();

    User findByUsername(String username);

    void addSubSubjectToUser(User user, SubSubject subSubject);

    void removeSubSubjectFromUser(User user, SubSubject subSubject);

    void save(User user);

    void merge(User user);

    void remove(Long id);

    Map<SubSubject, List<User>> getWaitingConsultantsBySubject(Subject subject);
}
