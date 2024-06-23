package com.group1.keke.controller;

import com.group1.keke.dao.StudentDao;
import com.group1.keke.dao.StudentTransactionDao;
import com.group1.keke.model.Student;
import com.group1.keke.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
public class TransactionController {

    StudentTransactionDao transactionDao;
    StudentDao studentDao;

    @Autowired
    public TransactionController(StudentTransactionDao transactionDao, StudentDao studentDao){

        this.transactionDao = transactionDao;
        this.studentDao = studentDao;
    }

    @GetMapping("/student/transactions")
    public ResponseEntity<List<Transaction>> getTransaction(Principal principal){
        String matric_no = principal.getName();
        Student student = studentDao.findStudentByMatricNo(matric_no);
        List<Transaction> transactions = transactionDao.getStudentTransactionsByMatric_No(student.getId()+"");
        return ResponseEntity.ok(transactions);
    }
}
