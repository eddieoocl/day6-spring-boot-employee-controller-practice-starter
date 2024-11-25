package com.oocl.springbootemployee.controller;

import com.oocl.springbootemployee.dto.EmployeeUpdateDTO;
import com.oocl.springbootemployee.model.Employee;
import com.oocl.springbootemployee.model.Gender;
import com.oocl.springbootemployee.repository.EmployeeRepository;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping()
    public List<Employee> getAll() {
        return this.employeeRepository.getAll();
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable int id) {
        return this.employeeRepository.getById(id);
    }

    @GetMapping(params = {"gender"})
    public List<Employee> getByGender(@RequestParam Gender gender) {
        return this.employeeRepository.getByGender(gender);
    }

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public Employee create(@RequestBody Employee employee) {
        return this.employeeRepository.create(employee);
    }

    @PutMapping(path = "{id}")
    public Employee update(@PathVariable int id, @RequestBody EmployeeUpdateDTO employeeUpdateDTO) {
        return this.employeeRepository.update(id, employeeUpdateDTO.getAge(), employeeUpdateDTO.getSalary());
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        this.employeeRepository.delete(id);
    }

    @GetMapping(params = {"page", "size"})
    public List<Employee> paginate(@RequestParam int page, @RequestParam int size) {
        return this.employeeRepository.paginate(page, size);
    }
}