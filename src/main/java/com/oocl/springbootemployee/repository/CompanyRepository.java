package com.oocl.springbootemployee.repository;

import com.oocl.springbootemployee.model.Company;
import com.oocl.springbootemployee.model.Gender;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class CompanyRepository {
    private final List<Company> companies = new ArrayList<>();

    public CompanyRepository() {
        companies.add(new Company(1, "name"));
        companies.add(new Company(2, "name2"));
        companies.add(new Company(3, "name3"));
    }

    public List<Company> getAll() {
        return companies;
    }
}