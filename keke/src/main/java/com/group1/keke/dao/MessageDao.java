package com.group1.keke.dao;

import com.group1.keke.model.DriverTransaction;
import com.group1.keke.model.Message;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MessageDao {
    EntityManager entityManager;

    @Autowired
    public MessageDao(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Transactional
    public List<Message> getMessagesByStudentIdAndDriverId(String student_id, String driver_id){
        System.out.println("In find Messages by student and driver id");
        List<Message> messages ;
        TypedQuery<Message> typedQuery = entityManager.createQuery(
                "FROM Message WHERE student_id= :student_id AND driver_id= :driver_id", Message.class
        );
        typedQuery.setParameter("student_id",student_id);
        typedQuery.setParameter("driver_id",driver_id);
        messages = typedQuery.getResultList();

        return messages;
    }

    @Transactional
    public void addMessage(Message message){
        entityManager.persist(message);
    }

    @Transactional
    public  Message getMessageByTime(String time){
        Message message;
        TypedQuery<Message> typedQuery = entityManager.createQuery(
                "FROM Message WHERE date = :date", Message.class
        );
        typedQuery.setParameter("date",time);
        message = typedQuery.getSingleResult();
        return message;
    }

}
