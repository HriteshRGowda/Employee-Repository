package com.example.hritesh.demo;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController
{
    @Autowired
    private EmployeeService service;

    @PostMapping("/add")
    public void addEmployee(@Valid @RequestBody Employee employee)
    {

        service.addEmployee(employee);
    }

    @GetMapping("/getEmployee")
    public List<Employee> getEmployee()
    {
        return service.getAllEmployees();
    }
}
