package com.group1.keke.controller;

import com.group1.keke.dao.DriverDao;
import com.group1.keke.dao.DriverTransactionDao;
import com.group1.keke.dao.StudentDao;
import com.group1.keke.dao.StudentTransactionDao;
import com.group1.keke.model.Driver;
import com.group1.keke.model.DriverTransaction;
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

    DriverTransactionDao driverTransactionDao;
    StudentDao studentDao;

    DriverDao driverDao;

    @Autowired
    public TransactionController(StudentTransactionDao transactionDao, DriverTransactionDao driverTransactionDao, StudentDao studentDao, DriverDao driverDao) {
        this.transactionDao = transactionDao;
        this.driverTransactionDao = driverTransactionDao;
        this.studentDao = studentDao;
        this.driverDao = driverDao;
    }


    @GetMapping("/student/transactions")
    public ResponseEntity<List<Transaction>> getTransaction(Principal principal){
        String matric_no = principal.getName();
        Student student = studentDao.findStudentByMatricNo(matric_no);
        if(student == null){
            return ResponseEntity.badRequest().body(null);
        }

        List<Transaction> transactions = transactionDao.getStudentTransactionsByStudentId(student.getId()+"");
        return ResponseEntity.ok(transactions);
    }
    @GetMapping("/driver/transactions")
    public ResponseEntity<List<DriverTransaction>> getDriverTransaction(Principal principal){
        String plate_no = principal.getName();
        Driver driver= driverDao.findDriverByPlate_no(plate_no);
        List<DriverTransaction> transactions = driverTransactionDao.getDriverTransactionByPlate_no(driver.getId()+"");
        return ResponseEntity.ok(transactions);
    }
}
