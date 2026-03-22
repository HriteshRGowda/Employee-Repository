package com.example.hritesh.demo;


import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class EmployeeControllerPIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldAddEmployee() throws Exception {
        String json = """
                {
                    "name":"Hritesh",
                    "department":"BTS"
                }
                """;
        // Step 1 : Add Employee
        mockMvc.perform(post("/employees/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());

        //Step 2:fetch Employee

        mockMvc.perform(get("/employees/getEmployee"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].name").value(hasItem("Hritesh")));

    }

    @Test
    void testMultipleEmployees() throws Exception {
        String emp1 = """
                {
                    "name":"Hritesh",
                    "department":"BTS"
                }
                """;

        String emp2 = """
                {
                    "name":"Virat Kohli",
                    "department":"Sales"
                }
                """;

        mockMvc.perform(post("/employees/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(emp1))
                .andExpect(status().isOk());

        mockMvc.perform(post("/employees/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(emp2))
                .andExpect(status().isOk());

        mockMvc.perform(get("/employees/getEmployee"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].name").value(hasItem("Hritesh")))
                .andExpect(jsonPath("$[*].name").value(hasItem("Virat Kohli")));

        mockMvc.perform(get("/employees/getEmployee"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].department").value(hasItem("BTS")))
                .andExpect(jsonPath("$[*].department").value(hasItem("Sales")));

    }

    @Test
    void testEmptyEMployeeData() throws Exception {
        mockMvc.perform(get("/employees/getEmployee"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());

    }
}
