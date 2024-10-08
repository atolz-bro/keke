package com.group1.keke.controller;


import com.group1.keke.dao.DriverDao;
import com.group1.keke.dao.MessageDao;
import com.group1.keke.dao.StudentDao;
import com.group1.keke.model.Driver;
import com.group1.keke.model.Message;
import com.group1.keke.model.MessageRequestObj;
import com.group1.keke.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class StudentMessagingController {


    MessageDao messageDao;
    StudentDao studentDao;
    DriverDao driverDao;
    @Autowired
    StudentMessagingController(MessageDao messageDao, StudentDao studentDao,DriverDao driverDao){
        this.messageDao = messageDao;
        this.studentDao = studentDao;
        this.driverDao = driverDao;
    }

    @PostMapping("/student/message")
    public ResponseEntity<Message> sendMessage(@RequestBody MessageRequestObj messageReq, Principal principal){
        Student student = studentDao.findStudentByMatricNo(principal.getName());
        Driver driver = driverDao.findDriverByPlate_no(messageReq.getPlate_no());
        System.out.println(principal.getName());
        String currTime = System.currentTimeMillis()+"";
        System.out.println(messageReq);
        Message message = new Message(
                messageReq.getMessage(),
                String.valueOf(student.getId()),
                String.valueOf(driver.getId()),student.getMatric_no(),currTime);

        messageDao.addMessage(message);
        Message messageDb = messageDao.getMessageByTime(currTime);
        return ResponseEntity.ok(messageDb);
    }

    @GetMapping("/student/message")
    public ResponseEntity<List<Message>> getMessages(@RequestParam String plate_no,Principal principal){
        Student student = studentDao.findStudentByMatricNo(principal.getName());
        Driver driver = driverDao.findDriverByPlate_no(plate_no);
        List<Message> messages = messageDao.getMessagesByStudentIdAndDriverId(String.valueOf(student.getId()),String.valueOf(driver.getId()));
        return ResponseEntity.ok(messages);
    }
}
