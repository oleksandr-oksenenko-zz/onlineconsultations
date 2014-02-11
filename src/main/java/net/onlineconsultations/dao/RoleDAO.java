package net.onlineconsultations.dao;

import net.onlineconsultations.domain.UserRole;

import java.util.List;

public interface RoleDAO {
    public List<UserRole> getAll();
}
