package com.oocl.springbootemployee.controller;

import com.oocl.springbootemployee.model.Employee;
import com.oocl.springbootemployee.repository.EmployRepository;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{employeeId}")
    public Employee getById(@PathVariable("employeeId") Integer employeeId){
        return employRepository.getById(employeeId);
    }

    @DeleteMapping("/{employeeId}")
    public boolean deleteById(@PathVariable("employeeId")Integer employeeId){
        return employRepository.deleteById(employeeId);
    }


}
