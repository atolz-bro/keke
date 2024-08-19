package com.group1.keke.controller;
import com.group1.keke.dao.DriverDao;
import com.group1.keke.dao.DriverTransactionDao;
import com.group1.keke.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Base64;
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

    @PostMapping("/driver/login")
    ResponseEntity<DriverLoginResponse> login(@RequestBody DriverLoginRequest driverLoginRequest) {
        String plate_no = driverLoginRequest.getDriver_plate_no();
        String password = driverLoginRequest.getPassword();
        System.out.println(plate_no + ":"+password);
        Driver driver = driverDao.findDriverByPlate_no(plate_no);
        if(driver == null){
            System.out.println("driver doesn't exist: "+plate_no);
            return ResponseEntity.badRequest().body(null);
        } else if (driver.getPassword().substring(6).equals(password)) {
            String base64LoginToken = encodeCredentials(plate_no,password);
            System.out.println("Login token: "+ base64LoginToken);
            DriverLoginResponse driverLoginResponse = new DriverLoginResponse("Basic "+base64LoginToken,driver.getUsername(),driver.getEmail(),driver.getPlate_no(),driver.getAcct_bal());
            return ResponseEntity.ok(driverLoginResponse);
        }else{
            System.out.println("Password attempt:" + password + " Driver db Pass: "+driver.getPassword().substring(6));
            return ResponseEntity.badRequest().body(null);
        }

    }
    public static String encodeCredentials(String plate_no, String password) {
        String credentials = plate_no + ":" + password;
        return Base64.getEncoder().encodeToString(credentials.getBytes());
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
                currTime,
                "");
        driverTransactionDao.saveDriverTransaction(driverTransaction);
        DriverTransaction driverTransactionfromDb = driverTransactionDao.findTransactionByTime(currTime);
        System.out.println("driverTransaction From DB:"+driverTransactionfromDb);
        return ResponseEntity.ok(updatedDriver);
    }

    @GetMapping("/driver/details")
    ResponseEntity<DriverDetailsResponse> getDriverDetails(@RequestParam String plate_no){

        Driver driver = driverDao.findDriverByPlate_no(plate_no);
        if(driver == null){
            return ResponseEntity.badRequest().body(null);
        }
        DriverDetailsResponse driverDetailsResponse = new DriverDetailsResponse(driver.getPlate_no(), driver.getUsername());
        return ResponseEntity.ok(driverDetailsResponse);
    }
    @GetMapping("/driver/balance")
    ResponseEntity<String> getDriverBalance(Principal principal){

        Driver driver = driverDao.findDriverByPlate_no(principal.getName());
        if(driver == null){
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(driver.getAcct_bal());
    }



}
