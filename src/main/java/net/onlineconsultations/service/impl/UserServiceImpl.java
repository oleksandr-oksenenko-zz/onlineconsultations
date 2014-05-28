package net.onlineconsultations.service.impl;

import net.onlineconsultations.dao.SubSubjectDao;
import net.onlineconsultations.dao.SubjectDao;
import net.onlineconsultations.dao.UserDAO;
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
    private SubSubjectDao subSubjectDao;

    @Inject
    private SubjectDao subjectDAO;

    @Inject
    private MessageDigestPasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public User getById(Long id) {
        return userDAO.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAll() {
        return userDAO.getAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        return userDAO.findByUsername(username);
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
        User oldUser = userDAO.getById(user.getId());
        if (!user.getPassword().isEmpty()) {
            String encodedPassword = passwordEncoder.encodePassword(user.getPassword(), null);

            user.setPassword(encodedPassword);
        } else {
            user.setPassword(oldUser.getPassword());
        }

        userDAO.merge(user);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        User user = userDAO.getById(id);
        userDAO.remove(user);
    }
}
