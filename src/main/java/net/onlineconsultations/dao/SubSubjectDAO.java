package net.onlineconsultations.dao;

import java.util.List;

import net.onlineconsultations.domain.SubSubject;

public interface SubSubjectDAO {
    List<SubSubject> getAll();

    SubSubject getById(Long id);

    void save(SubSubject subSubject);
}
