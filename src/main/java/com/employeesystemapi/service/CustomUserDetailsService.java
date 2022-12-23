package com.employeesystemapi.service;

import com.employeesystemapi.entity.EmployeeEntity;
import com.employeesystemapi.exception.UserEmailNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService  {
    @Autowired
    private IEmployeeService employeeService;


    public UserDetails getUserByEmail(String email) {
        try {
            EmployeeEntity employeeEntity = employeeService.getEmployeeByEmail(email);
            return (UserDetails) employeeEntity;
        } catch (Exception e) {
            throw new UserEmailNotFoundException("Employee not found");
        }
    }
}
