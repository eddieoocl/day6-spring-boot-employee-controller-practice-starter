package com.oocl.springbootemployee.repository;

import com.oocl.springbootemployee.model.Employee;
import com.oocl.springbootemployee.model.Gender;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepository {
    private final List<Employee> employees = new ArrayList<>();

    public EmployeeRepository() {
        employees.add(new Employee(1, "name", 18, Gender.FEMALE, 8000.2));
        employees.add(new Employee(2, "name2", 18, Gender.MALE, 8000.2));
        employees.add(new Employee(3, "name3", 18, Gender.FEMALE, 8000.2));
    }

    public List<Employee> getAll() {
        return employees;
    }

    public Employee getById(int id) {
        return employees.stream().filter(employee -> employee.getId() == id).findFirst().orElse(null);
    }

    public List<Employee> getByGender(Gender gender) {
        return employees.stream().filter(employee -> employee.getGender().equals(gender)).collect(Collectors.toList());
    }

    public Employee create(Employee employee) {
        employee.setId(employees.size() + 1);
        employees.add(employee);
        return employee;
    }
}