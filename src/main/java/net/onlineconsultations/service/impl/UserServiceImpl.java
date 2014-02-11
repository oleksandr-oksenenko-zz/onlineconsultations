package net.onlineconsultations.service.impl;

import net.onlineconsultations.dao.UserDAO;
import net.onlineconsultations.domain.User;
import net.onlineconsultations.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

@Service
public class UserServiceImpl implements UserService {
    @Inject
    private UserDAO userDAO;

    @Override
    public User getById(Long id) {
        return userDAO.getById(id);
    }

    @Override
    public User findByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    @Override
    @Transactional
    public void save(User user) {
        userDAO.save(user);
    }

}
