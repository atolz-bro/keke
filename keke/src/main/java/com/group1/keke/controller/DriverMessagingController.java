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
public class DriverMessagingController {

    MessageDao messageDao;
    StudentDao studentDao;
    DriverDao driverDao;

    @Autowired
    public DriverMessagingController(MessageDao messageDao, StudentDao studentDao, DriverDao driverDao) {
        this.messageDao = messageDao;
        this.studentDao = studentDao;
        this.driverDao = driverDao;
    }

    @PostMapping("/driver/message")
    public ResponseEntity<Message> sendMessage(@RequestBody MessageRequestObj messageReq, Principal principal){
        Student student = studentDao.findStudentByMatricNo(messageReq.getMatric_no());
        Driver driver = driverDao.findDriverByPlate_no(principal.getName());
        if(student == null || driver == null){
            return ResponseEntity.badRequest().body(null);
        }
        System.out.println(principal.getName());
        String currTime = System.currentTimeMillis()+"";
        System.out.println(messageReq);
        Message message = new Message(
                messageReq.getMessage(),
                String.valueOf(student.getId()),
                String.valueOf(driver.getId()),driver.getPlate_no(),currTime);

        messageDao.addMessage(message);
        Message messageDb = messageDao.getMessageByTime(currTime);
        return ResponseEntity.ok(messageDb);
    }

    @GetMapping("/driver/message")
    public ResponseEntity<List<Message>> getMessages(@RequestParam String matric_no, Principal principal){

        Student student = studentDao.findStudentByMatricNo(matric_no);
        if(student ==null){
            return ResponseEntity.badRequest().body(null);
        }
        Driver driver = driverDao.findDriverByPlate_no(principal.getName());
        List<Message> messages = messageDao.getMessagesByStudentIdAndDriverId(String.valueOf(student.getId()),String.valueOf(driver.getId()));
        return ResponseEntity.ok(messages);

    }
}
