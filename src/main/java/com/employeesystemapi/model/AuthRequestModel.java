package com.employeesystemapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthRequestModel {
    private String email;
    private String password;
}
