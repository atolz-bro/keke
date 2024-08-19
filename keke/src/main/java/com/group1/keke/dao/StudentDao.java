package com.group1.keke.dao;

import com.group1.keke.model.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDao {

    EntityManager entityManager;
    @Autowired
    public StudentDao(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Transactional
    public void addNewStudent(Student student) {
        System.out.println("Add New Student");
        entityManager.persist(student);
    }

    @Transactional
    public Student findStudentByMatricNo(String matric){
        System.out.println("In find Student by ID");
        Student theStudent;
        try{
            TypedQuery<Student> typedQuery = entityManager.createQuery(
                    "FROM Student WHERE matric_no= :matric", Student.class
            );
            System.out.println("In find Student by ID");
            typedQuery.setParameter("matric",matric);
            theStudent = typedQuery.getSingleResult();
            System.out.println("In find Student By matric"+ theStudent);

        }catch (NoResultException nre){
            theStudent = null;
        }

        return theStudent;
    }

    @Transactional

    public Student updateStudentWithNewBalance(Student student){
        return entityManager.merge(student);
    }


}
