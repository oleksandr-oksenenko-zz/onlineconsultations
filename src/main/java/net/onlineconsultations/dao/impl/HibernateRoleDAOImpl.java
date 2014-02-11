package net.onlineconsultations.dao.impl;

import net.onlineconsultations.dao.RoleDAO;
import net.onlineconsultations.domain.UserRole;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HibernateRoleDAOImpl extends HibernateBaseDAO implements RoleDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public List<UserRole> getAll() {
        return getSession().createCriteria(UserRole.class).list();
    }
}
