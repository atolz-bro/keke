package com.group1.keke.controller;

import com.group1.keke.dao.DriverDao;
import com.group1.keke.model.Driver;
import com.group1.keke.model.Student;
import com.group1.keke.dao.StudentDao;
import com.group1.keke.security.SecurityConfig;
import org.springframework.http.HttpStatusCode;
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
    @PostMapping("/student/register")
    public ResponseEntity<Student> registerStudent(@RequestBody Student student){
        if (studentExist(student.getMatric_no())){
            return ResponseEntity.status(HttpStatusCode.valueOf(409)).body(null);
        }
        student.setAccountBal(0+"");
        student.setPassword("{noop}"+student.getPassword());
        studentDao.addNewStudent(student);

        Student studentFromDb;

        String matric_no = student.getMatric_no();
        studentFromDb = studentDao.findStudentByMatricNo(matric_no);

        return ResponseEntity.ok(studentFromDb);
    }

    private boolean studentExist(String matricNo) {
        Student student = studentDao.findStudentByMatricNo(matricNo);
        if(student == null)
            return false;
        else return true;
    }

    @PostMapping(SecurityConfig.DRIVER_REGISTER_URL)
    public ResponseEntity<Driver> registerDriver(@RequestBody Driver driver){
        if(driverExist(driver.getPlate_no())){
            return ResponseEntity.status(HttpStatusCode.valueOf(409)).body(null);
        }
        driver.setAcct_bal("0");
        driver.setPassword("{noop}"+driver.getPassword());
        driverDao.addNewDriver(driver);
        Driver driverFromDb;
        String plate_no = driver.getPlate_no();
        driverFromDb = driverDao.findDriverByPlate_no(plate_no);
        return ResponseEntity.ok(driverFromDb);
    }

    private boolean driverExist(String plateNo) {
        Driver driver = driverDao.findDriverByPlate_no(plateNo);
        if(driver == null)
            return false;
        else
            return true;
    }


}

