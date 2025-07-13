package com.foxdev.project.findJobProject.service.impl;

import com.foxdev.project.findJobProject.domain.Company;
import com.foxdev.project.findJobProject.domain.dto.company.PostCompanyDTO;
import com.foxdev.project.findJobProject.exception.CompanyAlreadyExistedException;
import com.foxdev.project.findJobProject.repository.CompanyRepository;
import com.foxdev.project.findJobProject.service.CompanyService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,  makeFinal = true)
public class CompanyServiceImpl implements CompanyService {
    CompanyRepository companyRepository;

    @Override
    public Company createCompany(PostCompanyDTO postCompanyDTO) {
        if (this.companyRepository.existsByName(postCompanyDTO.getName())) throw new CompanyAlreadyExistedException("Company already existed");
        Company company = new Company();
        company.setName(postCompanyDTO.getName());
        company.setAddress(postCompanyDTO.getAddress());
        company.setDescription(postCompanyDTO.getDescription());
        company.setLogo(postCompanyDTO.getLogo());
        return this.companyRepository.save(company);
    }

    @Override
    public List<Company> getAllCompanies() {
        return this.companyRepository.findAll();
    }

    @Override
    public Company getCompanyById(Long id) {
        return null;
    }
}
