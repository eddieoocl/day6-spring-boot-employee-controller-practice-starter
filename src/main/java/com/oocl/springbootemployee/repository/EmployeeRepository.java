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

    public Employee update(int id, int age, double salary) {
        Employee updatedEmployee = employees.stream().filter(employee -> employee.getId() == id).findFirst().orElse(null);
        if (updatedEmployee == null) {
            return null;
        }
        updatedEmployee.setAge(age);
        updatedEmployee.setSalary(salary);
        return updatedEmployee;
    }

    public void delete(int id) {
        employees.removeIf(employee -> employee.getId() == id);
    }

    public List<Employee> paginate(int page, int size) {
        try {
            int fromIndex = Math.max(0, Math.min((page - 1) * size, employees.size()));
            int toIndex = Math.max(0, Math.min(page * size, employees.size()));
            return employees.subList(fromIndex, toIndex);
        } catch (IndexOutOfBoundsException exception) {
            return new ArrayList<>();
        }
    }
}