package com.employeesystemapi.service;


import com.employeesystemapi.entity.EmployeeEntity;
import com.employeesystemapi.exception.UserEmailNotFoundException;
import com.employeesystemapi.model.EditEmployeeModel;
import com.employeesystemapi.model.EmployeeModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IEmployeeService {
    boolean saveEmployee(EmployeeModel employeeModel);

    boolean editEmployee(Long employeeId, EditEmployeeModel employeeModel);

    boolean deleteEmployee(long employeeId);

    Page<EmployeeEntity> getEmployees(Pageable pageable);

    EmployeeEntity getEmployeeByEmail (String email) throws UserEmailNotFoundException;
}
