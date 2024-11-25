package com.oocl.springbootemployee.controller;

import com.oocl.springbootemployee.model.Employee;
import com.oocl.springbootemployee.model.Gender;
import com.oocl.springbootemployee.repository.EmployRepository;
import org.apache.catalina.core.ApplicationContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.ModelResultMatchers;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
//import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class EmployeeControllerTest {
    @Autowired
    private MockMvc client;
    @Autowired
    private EmployRepository employRepository;

    @Autowired
    JacksonTester<List<Employee>> jsonList;

    @Autowired
    JacksonTester<Employee> json;

    @BeforeEach
    void setup() {
        employRepository.getAll().clear();
//        employRepository.save(new Employee(1,"name1", 15,Gender.FEMALE,18.0));
//        employRepository.save(new Employee(2,"name2", 15,Gender.MALE,18.0));
//        employRepository.save(new Employee(3,"name3", 15,Gender.FEMALE,18.0));
    }

    @Test
    void should_return_employees_when_getAll_given_employeeRepository() throws Exception {
        //Given
        final List<Employee> givenEmployees = employRepository.getAll();
        //When
        client.perform(MockMvcRequestBuilders.get("/employees"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value(givenEmployees.get(0).getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value(givenEmployees.get(1).getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].name").value(givenEmployees.get(2).getName()));
        //Then

    }

    @Test
    void should_return_employee_when_getById_given_employeeId() throws Exception {
        //Given
        final Employee givenEmployee = employRepository.getById(1);
        //When
        client.perform(MockMvcRequestBuilders.get("/employees/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(givenEmployee.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(givenEmployee.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.gender").value(givenEmployee.getGender().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.salary").value(givenEmployee.getSalary()));
        //Then

    }

    @Test
    void should_return_male_employee_when_getByGender_given_male() throws Exception {
        //Given
        final List<Employee> givenEmployees = employRepository.getByGender(Gender.MALE);

        //When
        String employeeJsonString = client.perform(MockMvcRequestBuilders.get("/employees").queryParam("gender", "MALE"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)))
                .andReturn().getResponse().getContentAsString();

        List<Employee> employees = jsonList.parseObject(employeeJsonString);

        assertThat(employees).usingRecursiveComparison().isEqualTo(givenEmployees);

        //Then

    }

    @Test
    void should_return_employee_when_create_employee_given_employee() throws Exception {
        //Given
        String employeeJson = " {\n" +
                "        \"name\": \"name1\",\n" +
                "        \"age\": 15,\n" +
                "        \"gender\": \"FEMALE\",\n" +
                "        \"salary\": 18.0\n" +
                "    }";
        Employee newEmployee = new Employee(1, "name1", 15, Gender.FEMALE, 18.0);

//        final Employee givenEmployee = employRepository.create(newEmployee);

        //When
        String employeeJsonString = client.perform(MockMvcRequestBuilders.post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(employeeJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn().getResponse().getContentAsString();

        Employee employee = json.parseObject(employeeJsonString);
        assertThat(employee).usingRecursiveComparison().isEqualTo(newEmployee);
        //Then

    }

//
//    @Test
//    void should_return_true_when_deleteById_given_employeeId()throws Exception{
//        //Given
//        final boolean givenBooleanResult=employRepository.deleteById(1);
//        //When
//        client.perform(MockMvcRequestBuilders.delete("/employees/1"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andReturn().getAsyncResult();
//
//    }

}