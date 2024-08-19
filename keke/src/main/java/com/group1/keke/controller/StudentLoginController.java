package com.group1.keke.controller;

import com.group1.keke.dao.StudentDao;
import com.group1.keke.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Base64;

@RestController
public class StudentLoginController {

    StudentDao studentDao;

    @Autowired
    public StudentLoginController(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @PostMapping("/student/login")
    ResponseEntity<StudentLoginResponse> login(@RequestBody StudentLoginRequest studentLoginRequest) {
        String matric_no = studentLoginRequest.getMatric_no();
        String password = studentLoginRequest.getPassword();
        System.out.println(matric_no + ":"+password);
        Student student = studentDao.findStudentByMatricNo(matric_no);
        if(student == null){
            System.out.println("Student does not exist:"+matric_no);
            return ResponseEntity.badRequest().body(null);
        } else if (student.getPassword().substring(6).equals(password)) {
            String base64LoginToken = encodeCredentials(matric_no,password);
            System.out.println("Login token: "+ base64LoginToken);
            StudentLoginResponse studentLoginResponse = new StudentLoginResponse(
                    "Basic "+base64LoginToken,
                    student.getUsername(),
                    student.getEmail(),
                    student.getMatric_no(),
                    student.getAccount_bal()
            );
            return ResponseEntity.ok(studentLoginResponse);
        }else{
            System.out.println("Passsword attemp:" + password + " Driver db Pass:"+student.getPassword().substring(6));
            return ResponseEntity.badRequest().body(null);
        }

    }
    private static String encodeCredentials(String matric_no, String password) {
        String credentials = matric_no + ":" + password;
        return Base64.getEncoder().encodeToString(credentials.getBytes());
    }

    @GetMapping("student/details")
    ResponseEntity<StudentDetailsResposnse> getStudentDetails(Principal principal){

        Student student = studentDao.findStudentByMatricNo(principal.getName());
        if(student == null){
            return ResponseEntity.badRequest().body(null);
        }
        StudentDetailsResposnse studentDetailsResposnse = new StudentDetailsResposnse(student.getMatric_no(),student.getUsername(),student.getEmail());
        return ResponseEntity.ok(studentDetailsResposnse);
    }

    @GetMapping("/student/balance")
    ResponseEntity<String> getStudentBalance(Principal principal){

        Student student = studentDao.findStudentByMatricNo(principal.getName());
        if(student == null){
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(student.getAccount_bal());
    }
}
