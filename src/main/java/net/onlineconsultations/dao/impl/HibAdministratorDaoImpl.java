package net.onlineconsultations.dao.impl;

import net.onlineconsultations.dao.AdministratorDao;
import net.onlineconsultations.domain.Administrator;
import org.springframework.stereotype.Repository;

@Repository
public class HibAdministratorDaoImpl extends HibGenericDaoImpl<Administrator> implements AdministratorDao {
    public HibAdministratorDaoImpl() {
        super(Administrator.class);
    }
}
