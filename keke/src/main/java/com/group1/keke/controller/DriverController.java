package com.group1.keke.controller;
import com.group1.keke.dao.DriverDao;
import com.group1.keke.dao.DriverTransactionDao;
import com.group1.keke.model.Driver;
import com.group1.keke.model.DriverTransaction;
import com.group1.keke.model.DriverWithdrawToBankRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
public class DriverController {


    DriverDao driverDao;
    DriverTransactionDao driverTransactionDao;

    @Autowired
    public DriverController(DriverDao driverDao, DriverTransactionDao driverTransactionDao) {
        this.driverDao = driverDao;
        this.driverTransactionDao = driverTransactionDao;
    }

    @GetMapping("/driver/login")
    ResponseEntity<Driver> login(Principal principal) {
        String plate_no = principal.getName();
        Driver driver = driverDao.findDriverByPlate_no(plate_no);
        return ResponseEntity.ok(driver);
    }

    @GetMapping("/driver/transactions")
    ResponseEntity<List<DriverTransaction>> transactions(Principal principal){
        String plate_no = principal.getName() ;
        Driver driver = driverDao.findDriverByPlate_no(plate_no);
        List<DriverTransaction> transactions = driverTransactionDao.getAllTransaction(driver.getId()+"");
        return ResponseEntity.ok(transactions);
    }

    @PostMapping("/driver/withdraw")
    ResponseEntity<Driver> withdrawToBank(@RequestBody DriverWithdrawToBankRequest withdrawToBank, Principal principal){
        String plate_no = principal.getName() ;
        Driver driver = driverDao.findDriverByPlate_no(plate_no);

        Integer amount = Integer.valueOf(withdrawToBank.getAmount());
        Integer oldBal = Integer.valueOf(driver.getAcct_bal());
        Integer newBal = oldBal - amount;
        driver.setAcct_bal(String.valueOf(newBal));
        Driver updatedDriver = driverDao.updateDriverWithNewBalance(driver);

        String currTime = System.currentTimeMillis()+"";

        //Add DriverTransaction
        DriverTransaction driverTransaction = new DriverTransaction(
                driver.getId()+"",
                "DEBIT",
                amount+"",
                currTime);
        driverTransactionDao.saveDriverTransaction(driverTransaction);
        DriverTransaction driverTransactionfromDb = driverTransactionDao.findTransactionByTime(currTime);
        System.out.println("driverTransaction From DB:"+driverTransactionfromDb);
        return ResponseEntity.ok(updatedDriver);
    }}
