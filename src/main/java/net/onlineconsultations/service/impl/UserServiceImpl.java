package net.onlineconsultations.service.impl;

import net.onlineconsultations.dao.UserDAO;
import net.onlineconsultations.domain.User;
import net.onlineconsultations.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Override
    public User getById(Long id) {
        return userDAO.getById(id);
    }

    @Override
    public User getByUsername(String username) {
        return userDAO.getByUsername(username);
    }

    @Override
    @Transactional
    public void save(User user) {
        userDAO.save(user);
    }

}
