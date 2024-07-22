package com.tinubu.insurance.service;

import com.tinubu.insurance.model.InsurancePolicy;

import java.util.List;
import java.util.Optional;

public interface InsurancePolicyService {
    List<InsurancePolicy> findAll();

    Optional<InsurancePolicy> findById(int id);

    InsurancePolicy save(InsurancePolicy policy);

    InsurancePolicy update(InsurancePolicy policy);

    void deleteById(int id);
}
