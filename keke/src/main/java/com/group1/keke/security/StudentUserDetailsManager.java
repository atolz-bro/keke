package com.group1.keke.security;

import com.group1.keke.dao.StudentDao;
import com.group1.keke.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


public class StudentUserDetailsManager implements UserDetailsManager {


    StudentDao studentDao;

    @Autowired
    StudentUserDetailsManager (StudentDao studentDao){
        this.studentDao = studentDao;
    }


    @Override
    public void createUser(UserDetails user) {

    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Loading Student For Security "+username);
        Student student = studentDao.findStudentByMatricNo(username);
        System.out.println("db student"+student);
        SimpleGrantedAuthority role = new SimpleGrantedAuthority("STUDENT");
        List<SimpleGrantedAuthority> authorities =  new ArrayList<SimpleGrantedAuthority>();
        authorities.add(role);
        return new User(student.getMatric_no(), student.getPassword(),authorities);
    }
}
