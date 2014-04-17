package net.onlineconsultations.service.impl;

import net.onlineconsultations.dao.UserDAO;
import net.onlineconsultations.domain.User;
import net.onlineconsultations.service.UserService;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Inject
    private UserDAO userDAO;

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
        userDAO.remove(user);
    }
}
