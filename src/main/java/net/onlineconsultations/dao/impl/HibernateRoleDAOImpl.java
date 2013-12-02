package net.onlineconsultations.dao.impl;

import java.util.List;

import net.onlineconsultations.dao.RoleDAO;
import net.onlineconsultations.domain.Role;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateRoleDAOImpl extends HibernateBaseDAO implements RoleDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public List<Role> getAll() {
	return getSession().createCriteria(Role.class).list();
    }
}
