package com.oocl.springbootemployee.controller;

import com.oocl.springbootemployee.model.Company;
import com.oocl.springbootemployee.model.Gender;
import com.oocl.springbootemployee.repository.CompanyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class CompanyControllerTest {
    @Autowired
    private MockMvc client;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    JacksonTester<Company> companyJacksonTester;

    @Autowired
    JacksonTester<List<Company>> companiesJacksonTester;
    
    @Test
    void should_return_companies_when_getAll_given_company_repository() throws Exception {
        // Given
        final List<Company> givenCompanies = companyRepository.getAll();

        // When
        // Then
        String companiesJsonString = client.perform(MockMvcRequestBuilders.get("/companies"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(givenCompanies.size())))
                .andReturn().getResponse().getContentAsString();

        List<Company> companies = companiesJacksonTester.parseObject(companiesJsonString);
        assertThat(companies).usingRecursiveComparison().isEqualTo(givenCompanies);
    }
}