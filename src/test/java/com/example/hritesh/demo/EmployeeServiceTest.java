package com.example.hritesh.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest
{
    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void shouldReturnAllEmployee()
    {
        List<Employee> employees = List.of(new Employee("Hritesh","BTS"));
        when(employeeRepository.findAll()).thenReturn(employees);
        List<Employee> result = employeeService.getAllEmployees();
        assertEquals(1,result.size());
        assertEquals("Hritesh",result.get(0).getName());
    }

    @Test
    void shouldReturnEmptyListWhenRepoReturnsEmpty()
    {
        when(employeeRepository.findAll()).thenReturn(List.of());
        List<Employee> result = employeeService.getAllEmployees();
        assertEquals(0,result.size());
    }

    @Test
    void shouldSaveEmployee()
    {
        Employee emp = new Employee("Hritesh","BTS");
        employeeService.addEmployee(emp);
        verify(employeeRepository).save(emp);
    }
}
