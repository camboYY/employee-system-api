package com.employeesystemapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class EditEmployeeModel {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String middleName;
    private String password;
}
