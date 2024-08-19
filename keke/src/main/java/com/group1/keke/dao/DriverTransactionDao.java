package com.group1.keke.dao;

import com.group1.keke.model.DriverTransaction;
import com.group1.keke.model.Transaction;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DriverTransactionDao {
    @Autowired
    EntityManager entityManager;
    public DriverTransactionDao(EntityManager entityManager){
        this.entityManager = entityManager;
    }


    @Transactional
    public void saveDriverTransaction(DriverTransaction transaction){
        entityManager.persist(transaction);

    }

    @Transactional
    public List<DriverTransaction> getAllTransaction(String driver_id){
        List<DriverTransaction> transactions;
        TypedQuery<DriverTransaction> transactionTypedQuery = entityManager.createQuery(
                "FROM DriverTransaction WHERE driver_id= :driverId",DriverTransaction.class
        );
        transactionTypedQuery.setParameter("driverId",driver_id);

        transactions = transactionTypedQuery.getResultList();
        System.out.println(transactions+" size: "+transactions.size());
        return transactions;
    }
    @Transactional
    public DriverTransaction findTransactionByTime(String transactionTime){
        System.out.println("In find DriverTransaction by time by ID");
        DriverTransaction transaction;
        TypedQuery<DriverTransaction> typedQuery = entityManager.createQuery(
                "FROM DriverTransaction WHERE date= :time", DriverTransaction.class
        );
        typedQuery.setParameter("time",transactionTime);
        transaction = typedQuery.getSingleResult();
        System.out.println("In find Student By matric"+ transaction);
        return transaction;
    }

    public List<DriverTransaction> getDriverTransactionByPlate_no(String driver_id) {
        List<DriverTransaction> transactions;
        TypedQuery<DriverTransaction> transactionTypedQuery = entityManager.createQuery(
                "FROM DriverTransaction WHERE driver_id= :driver_id",DriverTransaction.class
        );
        transactionTypedQuery.setParameter("driver_id",driver_id);

        transactions = transactionTypedQuery.getResultList();
        System.out.println(transactions+" size: "+transactions.size());
        return transactions;
    }
}
