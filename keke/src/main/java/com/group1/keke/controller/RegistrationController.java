package com.group1.keke.controller;

import com.group1.keke.dao.DriverDao;
import com.group1.keke.model.Driver;
import com.group1.keke.model.Student;
import com.group1.keke.dao.StudentDao;
import com.group1.keke.security.SecurityConfig;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api")
public class RegistrationController {

    StudentDao studentDao;
    DriverDao driverDao;


    RegistrationController(StudentDao studentDao,DriverDao driverDao){
        this.studentDao = studentDao;
        this.driverDao = driverDao;
    }
    @PostMapping("/register/student")
    public ResponseEntity<Student> registerStudent(@RequestBody Student student){
        student.setAccountBal(0+"");
        studentDao.addNewStudent(student);

        Student studentFromDb;

        String matric_no = student.getMatric_no();
        studentFromDb = studentDao.findStudentByMatricNo(matric_no);

        return ResponseEntity.ok(studentFromDb);
    }

    @PostMapping(SecurityConfig.DRIVER_REGISTER_URL)
    public ResponseEntity<Driver> registerDriver(@RequestBody Driver driver){
        driverDao.addNewDriver(driver);
        Driver driverFromDb;
        String plate_no = driver.getPlate_no();
        driverFromDb = driverDao.findDriverByPlate_no(plate_no);
        return ResponseEntity.ok(driverFromDb);
    }



}

