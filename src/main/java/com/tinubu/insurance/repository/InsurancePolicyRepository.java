package com.tinubu.insurance.repository;

import com.tinubu.insurance.model.InsurancePolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface InsurancePolicyRepository extends JpaRepository<InsurancePolicy, Integer> {
}
