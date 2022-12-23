package com.employeesystemapi.service;

import com.employeesystemapi.entity.EmployeeEntity;
import com.employeesystemapi.exception.UserEmailNotFoundException;
import com.employeesystemapi.model.EditEmployeeModel;
import com.employeesystemapi.model.EmployeeModel;
import com.employeesystemapi.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public boolean saveEmployee(EmployeeModel employeeModel) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        BeanUtils.copyProperties(employeeModel, employeeEntity);
        EmployeeEntity employeeEntityResponse = employeeRepository.save(employeeEntity);
        if(employeeEntityResponse != null)
        return true;

        return false;
    }

    @Override
    public boolean editEmployee(Long employeeId, EditEmployeeModel employeeModel) {

        Optional<EmployeeEntity> checkEmployeeExist = employeeRepository.findById(employeeId);

        if(checkEmployeeExist.isPresent()) {
            EmployeeEntity employeeEnt = checkEmployeeExist.get();
            employeeEnt.setEmail(employeeModel.getEmail());
            employeeEnt.setFirstName(employeeModel.getFirstName());
            employeeEnt.setLastName(employeeModel.getLastName());
            employeeEnt.setPhoneNumber(employeeModel.getPhoneNumber());
            employeeEnt.setMiddleName(employeeModel.getMiddleName());
           EmployeeEntity employeeEntity1 =  employeeRepository.save(employeeEnt);
           if(employeeEntity1 != null) return true;
        }

        return false;
    }

    @Override
    public boolean deleteEmployee(long employeeId) {
        try {
            employeeRepository.deleteById(employeeId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Page<EmployeeEntity> getEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    @Override
    public EmployeeEntity getEmployeeByEmail(String email) throws UserEmailNotFoundException {
        try {
            return employeeRepository.findByEmail(email);
        }catch (UserEmailNotFoundException e) {
            throw new UserEmailNotFoundException("User is not found by this email: "+ email);
        }
    }


}
