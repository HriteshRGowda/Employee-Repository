package com.example.hritesh.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
public class Employee
{
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private Long id;
       @NotBlank
       private String name;
       @NotBlank
       private String department;

       public Employee() {}

       public Employee(String name, String department) {
              this.name = name;
              this.department = department;
       }

       public Long getId() { return id; }

       public String getName() { return name; }

       public String getDepartment() { return department; }

       public void setName(String name) { this.name = name; }

       public void setDepartment(String department) { this.department = department; }
}
