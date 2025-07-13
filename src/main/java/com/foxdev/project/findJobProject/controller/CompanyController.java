package com.foxdev.project.findJobProject.controller;

import com.foxdev.project.findJobProject.domain.Company;
import com.foxdev.project.findJobProject.domain.dto.company.PostCompanyDTO;
import com.foxdev.project.findJobProject.service.CompanyService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CompanyController {
    CompanyService companyService;

    @PostMapping
    public ResponseEntity<Company> createCompany(@Valid @RequestBody PostCompanyDTO postCompanyDTO) {
        Company company = this.companyService.createCompany(postCompanyDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(company);
    }

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies() {
        List<Company> companies = this.companyService.getAllCompanies();
        return ResponseEntity.status(HttpStatus.OK).body(companies);
    }
}
