package com.tinubu.insurance.service;

import com.tinubu.insurance.model.InsurancePolicy;
import com.tinubu.insurance.repository.InsurancePolicyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InsurancePolicyServiceImpl implements InsurancePolicyService {

    private final InsurancePolicyRepository repository;

    public InsurancePolicyServiceImpl(InsurancePolicyRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<InsurancePolicy> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<InsurancePolicy> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public InsurancePolicy save(InsurancePolicy policy) {
        return repository.save(policy);
    }

    @Override
    public InsurancePolicy update(InsurancePolicy policy) {
        return repository.save(policy);
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }
}
