package net.onlineconsultations.service.impl;

import net.onlineconsultations.dao.AdministratorDao;
import net.onlineconsultations.domain.Administrator;
import net.onlineconsultations.service.AdministratorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

@Service
public class AdministratorServiceImpl implements AdministratorService {
    @Inject
    private AdministratorDao administratorDao;

    @Override
    @Transactional(readOnly = true)
    public Administrator getById(Long id) {
        return administratorDao.getById(id);
    }
}
