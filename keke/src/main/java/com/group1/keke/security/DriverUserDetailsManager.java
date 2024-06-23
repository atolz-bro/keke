package com.group1.keke.security;

import com.group1.keke.dao.DriverDao;
import com.group1.keke.model.Driver;
import com.group1.keke.model.DriverTransaction;
import com.group1.keke.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;

import java.util.ArrayList;
import java.util.List;

public class DriverUserDetailsManager implements UserDetailsManager {

    @Autowired
    DriverDao driverDao;

    DriverUserDetailsManager(DriverDao driverDao){
        this.driverDao = driverDao;
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
        Driver driver = driverDao.findDriverByPlate_no(username);
        SimpleGrantedAuthority role = new SimpleGrantedAuthority("DRIVER");
        List<SimpleGrantedAuthority> authorities =  new ArrayList<SimpleGrantedAuthority>();
        authorities.add(role);
        return new User(driver.getPlate_no(), driver.getPassword(),authorities);
    }
}
