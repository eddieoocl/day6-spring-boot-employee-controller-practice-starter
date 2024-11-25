package com.oocl.springbootemployee.controller;

import com.oocl.springbootemployee.model.Employee;
import com.oocl.springbootemployee.repository.EmployRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private EmployRepository employRepository;

    public EmployeeController(EmployRepository employRepository) {
        this.employRepository = employRepository;
    }

    @GetMapping
    public List<Employee> getAll() {
        return employRepository.getAll();
    }



}
