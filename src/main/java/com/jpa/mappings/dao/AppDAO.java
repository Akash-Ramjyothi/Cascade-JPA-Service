package com.jpa.mappings.dao;

import com.jpa.mappings.entity.Instructor;

import java.util.List;

public interface AppDAO {

    void save(Instructor instructor);

    Instructor findInstructorById(int id);

    List<Instructor> findAll();

    Instructor update(Instructor instructor);

    void deleteInstructorById(int id);

    boolean existsById(int id);

    long count();
}
