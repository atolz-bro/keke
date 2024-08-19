package com.group1.keke.security;

import com.group1.keke.dao.DriverDao;
import com.group1.keke.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    public static final String DRIVER_REGISTER_URL = "/driver/register";

    @Autowired
    StudentDao studentDao;

    @Autowired
    DriverDao driverDao;


    @Autowired
    public SecurityConfig(StudentDao studentDao){
        this.studentDao = studentDao;
    }
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(x->x.disable())
                .authorizeHttpRequests(c->{
                    c.anyRequest().authenticated();

                }).httpBasic(Customizer.withDefaults());
        http.securityMatcher("/student/fundwallet","/student/transfer","/student/transactions","/student/message","/student/balance","student/details");
        http.userDetailsService(new StudentUserDetailsManager(studentDao));

        return http.build();
    }

    @Bean
    SecurityFilterChain securityFilterChain2(HttpSecurity http) throws Exception {
        http.csrf(x->x.disable())
                .authorizeHttpRequests(c->{
                    c.anyRequest().authenticated();

                }).httpBasic(Customizer.withDefaults());
        http.securityMatcher("/driver/transactions","/driver/withdraw","/driver/message","/driver/balance");
        http.userDetailsService(new DriverUserDetailsManager(driverDao));

        return http.build();
    }




}
