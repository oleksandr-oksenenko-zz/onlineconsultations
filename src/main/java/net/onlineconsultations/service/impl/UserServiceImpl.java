package net.onlineconsultations.service.impl;

import net.onlineconsultations.dao.SubSubjectDAO;
import net.onlineconsultations.dao.UserDAO;
import net.onlineconsultations.domain.SubSubject;
import net.onlineconsultations.domain.User;
import net.onlineconsultations.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Inject
    private UserDAO userDAO;

    @Inject
    private SubSubjectDAO subSubjectDAO;

    @Inject
    private MessageDigestPasswordEncoder passwordEncoder;

    @Override
    public User getById(Long id) {
        return userDAO.getById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAll();
    }

    @Override
    public User findByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    @Override
    @Transactional
    public void addSubSubjectToUser(User user, SubSubject subSubject) {
        user = userDAO.getById(user.getId());
        subSubject = subSubjectDAO.getById(subSubject.getId());

        user.getUserSubSubjects().add(subSubject);

        userDAO.merge(user);
    }

    @Override
    @Transactional
    public void removeSubSubjectFromUser(User user, SubSubject subSubject) {
        user = userDAO.getById(user.getId());
        subSubject = subSubjectDAO.getById(subSubject.getId());

        user.getUserSubSubjects().remove(subSubject);
        userDAO.merge(user);
    }

    @Override
    @Transactional
    public void save(User user) {
        String encodedPassword = passwordEncoder.encodePassword(user.getPassword(), null);

        user.setPassword(encodedPassword);

        userDAO.save(user);
    }

    @Override
    @Transactional
    public void merge(User user) {
        String encodedPassword = passwordEncoder.encodePassword(user.getPassword(), null);

        user.setPassword(encodedPassword);

        userDAO.merge(user);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        User user = userDAO.getById(id);
        for (SubSubject subSubject : user.getUserSubSubjects()) {
            subSubject.getSubSubjectUsers().remove(user);
        }
        userDAO.remove(user);
    }
}
