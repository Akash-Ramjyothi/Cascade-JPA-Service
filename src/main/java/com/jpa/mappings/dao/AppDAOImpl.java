package com.jpa.mappings.dao;

import com.jpa.mappings.entity.Instructor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AppDAOImpl implements AppDAO {

    private final EntityManager entityManager;

    @Autowired
    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {
        if (instructor == null) {
            throw new IllegalArgumentException("Instructor cannot be null");
        }
        entityManager.persist(instructor);
        // log.debug("Instructor saved: {}", instructor);
    }

    @Override
    public Instructor findInstructorById(int id) {
        Instructor instructor = entityManager.find(Instructor.class, id);
        if (instructor == null) {
            // log.warn("Instructor not found with id: {}", id);
        }
        return instructor;
    }

    @Override
    @Transactional
    public Instructor update(Instructor instructor) {
        if (instructor == null) {
            throw new IllegalArgumentException("Instructor cannot be null");
        }
        Instructor updated = entityManager.merge(instructor);
        // log.debug("Instructor updated: {}", updated);
        return updated;
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {
        Instructor instructor = entityManager.find(Instructor.class, id);

        if (instructor == null) {
            throw new RuntimeException("Instructor not found with id: " + id);
        }

        entityManager.remove(instructor);
        // log.debug("Instructor deleted with id: {}", id);
    }

    @Override
    public boolean existsById(int id) {
        return entityManager.find(Instructor.class, id) != null;
    }
}
