package com.tinubu.insurance.it;

import com.tinubu.insurance.model.InsurancePolicy;
import com.tinubu.insurance.model.PolicyStatus;
import com.tinubu.insurance.repository.InsurancePolicyRepository;
import com.tinubu.insurance.service.InsurancePolicyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class InsurancePolicyServiceIT {

    @Autowired
    private InsurancePolicyService service;

    @Autowired
    private InsurancePolicyRepository repository;

    private InsurancePolicy policy;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
        policy = new InsurancePolicy("Integration Test Policy", PolicyStatus.ACTIVE, LocalDateTime.now(), LocalDateTime.now().plusYears(1));
        policy = repository.save(policy);
    }

    @Test
    void testFindAll() {
        List<InsurancePolicy> policies = service.findAll();
        assertFalse(policies.isEmpty());
    }

    @Test
    void testFindById() {
        Optional<InsurancePolicy> foundPolicy = service.findById(policy.getId());
        assertTrue(foundPolicy.isPresent());
        assertEquals(policy.getName(), foundPolicy.get().getName());
    }

    @Test
    void testSave() {
        InsurancePolicy newPolicy = new InsurancePolicy("New Policy", PolicyStatus.INACTIVE, LocalDateTime.now(), LocalDateTime.now().plusYears(1));
        InsurancePolicy savedPolicy = service.save(newPolicy);
        assertTrue(savedPolicy.getId() > 0);
    }

    @Test
    void testUpdate() {
        policy.setName("Updated Policy Name");
        InsurancePolicy updatedPolicy = service.update(policy);
        assertEquals("Updated Policy Name", updatedPolicy.getName());
    }

    @Test
    void testDeleteById() {
        service.deleteById(policy.getId());
        Optional<InsurancePolicy> deletedPolicy = service.findById(policy.getId());
        assertFalse(deletedPolicy.isPresent());
    }
}
