package com.employeesystemapi.controller;

import com.employeesystemapi.entity.EmployeeEntity;
import com.employeesystemapi.exception.EmployeeBadRequestException;
import com.employeesystemapi.model.EditEmployeeModel;
import com.employeesystemapi.model.EmployeeModel;
import com.employeesystemapi.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private IEmployeeService iEmployeeService;

    @PostMapping("/employees")
    public ResponseEntity<String> saveEmployee (@RequestBody EmployeeModel employeeModel) {
      boolean success =  this.iEmployeeService.saveEmployee(employeeModel);

      if(success)
       return ResponseEntity.ok("Success");

      throw new EmployeeBadRequestException("Bad Request");
    }

    @PutMapping("/employees/{employeeId}")
    public ResponseEntity<String> editEmployee (@RequestBody EditEmployeeModel employeeModel, @PathVariable("employeeId") Long employeeId) {
       boolean success = iEmployeeService.editEmployee(employeeId, employeeModel);
       if(success) return ResponseEntity.ok("Success");
       throw new EmployeeBadRequestException("Bad Request");
    }

    @DeleteMapping("/employees/{employeeId}")
    public ResponseEntity<String> deleteEmployee (@PathVariable("employeeId") long employeeId) {
        boolean success = iEmployeeService.deleteEmployee(employeeId);
        if(success) {
            return ResponseEntity.ok("Success");
        }
        throw new EmployeeBadRequestException("Bad Request");
    }

    @GetMapping("/employees")
    public ResponseEntity<Page<EmployeeEntity>> getEmployees (@RequestParam(value = "page", defaultValue ="0") int page,
                                                             @RequestParam(value = "size", defaultValue = "10") int size,
                                                              @RequestParam(value = "sort", defaultValue = "firstName") String sort
                                                            ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));

        Page<EmployeeEntity> employees = iEmployeeService.getEmployees(pageable);
        return ResponseEntity.ok(employees);
    }

}
