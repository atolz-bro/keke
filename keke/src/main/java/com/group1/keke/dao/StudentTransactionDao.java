package com.group1.keke.dao;

import com.group1.keke.model.Transaction;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentTransactionDao {

    EntityManager entityManager;

    StudentTransactionDao(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Transactional
    public void addNewTransaction(Transaction transaction){
        entityManager.persist(transaction);
    }

    @Transactional
    public List<Transaction> getStudentTransactionsByStudentId(String student_id){
        List<Transaction> transactions;
        TypedQuery<Transaction> transactionTypedQuery = entityManager.createQuery(
                "FROM Transaction WHERE student_id= :student_id",Transaction.class
        );
        transactionTypedQuery.setParameter("student_id",student_id);

        transactions = transactionTypedQuery.getResultList();
        System.out.println(transactions+" size: "+transactions.size());
        return transactions;
    }

    @Transactional
    public Transaction findTransactionByTime(String transactionTime){
        System.out.println("In find Transaction by time by ID");
        Transaction transaction;
        TypedQuery<Transaction> typedQuery = entityManager.createQuery(
                "FROM Transaction WHERE date= :time", Transaction.class
        );
        typedQuery.setParameter("time",transactionTime);
        transaction = typedQuery.getSingleResult();
        System.out.println("In find Student By matric"+ transaction);
        return transaction;
    }


}
