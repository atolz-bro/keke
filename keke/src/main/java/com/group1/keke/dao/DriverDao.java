package com.group1.keke.dao;

import com.group1.keke.model.Driver;
import com.group1.keke.model.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DriverDao {
    EntityManager entityManager;
    @Autowired
    public DriverDao(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Transactional
    public void addNewDriver(Driver driver) {
        entityManager.persist(driver);
    }

    @Transactional
    public Driver findDriverByPlate_no(String plate_no){
        Driver theDriver;
        TypedQuery<Driver> typedQuery = entityManager.createQuery(
                "FROM Driver WHERE plate_no= :plate_no", Driver.class
        );
        typedQuery.setParameter("plate_no",plate_no);
        try {
            theDriver = typedQuery.getSingleResult();
        }catch (Exception e){
            theDriver = null;
        }

        return theDriver;
    }


    @Transactional

    public Driver updateDriverWithNewBalance(Driver driver){
        return entityManager.merge(driver);
    }

}
