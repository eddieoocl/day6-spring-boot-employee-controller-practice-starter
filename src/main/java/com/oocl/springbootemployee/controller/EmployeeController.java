package com.oocl.springbootemployee.controller;

import com.oocl.springbootemployee.model.Employee;
import com.oocl.springbootemployee.model.Gender;
import com.oocl.springbootemployee.repository.EmployRepository;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
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
    public Employee getById(@PathVariable("employeeId") Integer employeeId) {
        return employRepository.getById(employeeId);
    }

    @DeleteMapping("/{employeeId}")
    public boolean deleteById(@PathVariable("employeeId") Integer employeeId) {
        return employRepository.deleteById(employeeId);
    }

    @GetMapping(params = {"gender"})
    public List<Employee> getByGender(@RequestParam Gender gender) {
        return employRepository.getByGender(gender);
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping
    public Employee create(@RequestBody Employee employee) {
        return employRepository.create(employee);
    }

}
