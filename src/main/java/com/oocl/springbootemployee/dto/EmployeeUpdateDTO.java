package com.oocl.springbootemployee.dto;

import jakarta.validation.constraints.NotNull;

public class EmployeeUpdateDTO {
    @NotNull
    private int age;

    @NotNull
    private Double salary;

    public int getAge() {
        return age;
    }

    public Double getSalary() {
        return salary;
    }
}
