package com.cg.service.company;

import com.cg.entity.Company;
import com.cg.repository.CompanyRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CompanyService implements ICompanyService{

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public Optional<Company> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public void save(Company company) {

    }

    @Override
    public void delete(Long aLong) {

    }
}
