package com.group1.keke.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ShutDownController {

    @Autowired
    private ApplicationContext context;

    @PostMapping("/shutdown")
    void shutdown() throws IOException {
        System.out.println("Shutdown Request Hello");
        //SpringApplication.exit(context, () -> 0);
        //String command = "cd C:\\Users\\atola\\OneDrive\\Desktop\\Final Year Project\\Offline_Payment_System\\offlinepaymentsystem && .\\mvnw spring-boot:run";
        //Process process = Runtime.getRuntime().exec(command);
        //System.out.println("still innn.....");
    }
}
