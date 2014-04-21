package net.onlineconsultations.dao;

import net.onlineconsultations.domain.SubSubject;
import net.onlineconsultations.domain.User;

import java.util.List;

public interface UserDAO extends GenericDao<User> {
    User findByUsername(String username);

    List<User> getOnlineConsultantsBySubSubject(SubSubject subSubject);
}
