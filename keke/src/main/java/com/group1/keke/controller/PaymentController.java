package com.group1.keke.controller;


import com.group1.keke.dao.DriverDao;
import com.group1.keke.dao.DriverTransactionDao;
import com.group1.keke.dao.StudentDao;
import com.group1.keke.dao.StudentTransactionDao;
import com.group1.keke.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class PaymentController {

    DriverDao driverDao;
    StudentDao studentDao;
    StudentTransactionDao studentTransactionDao;

    DriverTransactionDao driverTransactionDao;
    @Autowired
    public PaymentController(
            StudentDao studentDao, StudentTransactionDao studentTransactionDao, DriverDao driverDao, DriverTransactionDao driverTransactionDao){
        this.studentDao = studentDao;
        this.studentTransactionDao = studentTransactionDao;
        this.driverDao = driverDao;
        this.driverTransactionDao = driverTransactionDao;
    }
    @PostMapping("/student/fundwallet")
    public ResponseEntity<RequestTransaction> fundWallet(@RequestBody FundWalletRequest payment, Principal principal){
        String matric_no = principal.getName();
        Student student = studentDao.findStudentByMatricNo(matric_no);
        if(student == null){
            return ResponseEntity.badRequest().body(null);
        }
        System.out.println("Student Before Fund: "+student);

        String amount = payment.getAmount();
        String newBal = String.valueOf(Integer.parseInt(student.getAccount_bal()) + Integer.parseInt(amount));

        //
        student.setAccountBal(newBal);
        System.out.println("Student After Fund: "+student);

        student = studentDao.updateStudentWithNewBalance(student);

        //Add Transaction
        String currTime = System.currentTimeMillis()+"";
        Transaction transaction = new Transaction(
                "SELF",
                "CREDIT",
                student.getId()+"",
                amount,currTime

        );
        studentTransactionDao.addNewTransaction(transaction);
        Transaction transactiondb = studentTransactionDao.findTransactionByTime(currTime);
        System.out.println("Added Transaction in Db" +  transactiondb);

        RequestTransaction requestTransaction
                = new RequestTransaction("Fund Wallet",amount,student.getAccount_bal());
        return ResponseEntity.ok(requestTransaction);
    }

    @PostMapping("/student/transfer")
    public ResponseEntity<RequestTransaction> transferToDriver(@RequestBody TransferRequest transferRequest, Principal principal){
        String matric_no = principal.getName();
        String driver_plate_no = transferRequest.getDriver_plate_no();
        Integer amount = Integer.valueOf(transferRequest.getAmount());

        Student student = studentDao.findStudentByMatricNo(matric_no);
        Driver driver = driverDao.findDriverByPlate_no(driver_plate_no);

        Integer oldBal = Integer.valueOf(student.getAccount_bal());

        //Debit Student
        Integer newBal = oldBal-amount;
        student.setAccountBal(String.valueOf(newBal));
        studentDao.updateStudentWithNewBalance(student);

        //Add Debit Transaction For Student
        String currTime = System.currentTimeMillis()+"";
        Transaction transaction = new Transaction(
                driver.getPlate_no(),
                "DEBIT",
                student.getId()+"",
                String.valueOf(amount),currTime

        );
        studentTransactionDao.addNewTransaction(transaction);
        Transaction transactiondb = studentTransactionDao.findTransactionByTime(currTime);
        System.out.println("Added Transaction in Db" +  transactiondb);

        //Credit Driver
        oldBal = Integer.valueOf(driver.getAcct_bal());
        newBal = oldBal + amount;
        driver.setAcct_bal(String.valueOf(newBal));
        driverDao.updateDriverWithNewBalance(driver);

        //Add Credit Transaction For Driver
        DriverTransaction driverTransaction = new DriverTransaction(
                driver.getId()+"",
                "CREDIT",
                amount+"",currTime,student.getMatric_no()
        );
        driverTransactionDao.saveDriverTransaction(driverTransaction);
        DriverTransaction driverTransactionFromDb = driverTransactionDao.findTransactionByTime(currTime);
        System.out.println("Added Transaction in Driver DB"+driverTransactionFromDb);


        RequestTransaction requestTransaction
                = new RequestTransaction("Transfer To Driver",amount.toString(),student.getAccount_bal());
        return ResponseEntity.ok(requestTransaction);

    }


    @GetMapping("/getstudent")
    public ResponseEntity<Student> getStudent(){
        return ResponseEntity.ok(studentDao.findStudentByMatricNo("jq"));
    }


}
