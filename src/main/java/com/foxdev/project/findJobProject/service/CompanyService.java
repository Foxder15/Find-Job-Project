package com.foxdev.project.findJobProject.service;

import com.foxdev.project.findJobProject.domain.Company;
import com.foxdev.project.findJobProject.domain.dto.company.PostCompanyDTO;

import java.util.List;

public interface CompanyService {
    Company createCompany(PostCompanyDTO postCompanyDTO);
    List<Company> getAllCompanies();
    Company getCompanyById(Long id);
}
