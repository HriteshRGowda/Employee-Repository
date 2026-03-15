package com.example.hritesh.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    public void addEmployee(Employee employee)
    {
        repository.save(employee);
    }


    public List<Employee> getAllEmployees()
    {
       return repository.findAll();
    }
}
