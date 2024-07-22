package com.tinubu.insurance;

import com.tinubu.insurance.model.InsurancePolicy;
import com.tinubu.insurance.model.PolicyStatus;
import com.tinubu.insurance.repository.InsurancePolicyRepository;
import com.tinubu.insurance.service.InsurancePolicyServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InsurancePolicyApplicationTests {

    @Mock
    private InsurancePolicyRepository repository;

    @InjectMocks
    private InsurancePolicyServiceImpl service;

    private InsurancePolicy policy;

    @BeforeEach
    void setUp() {
        policy = new InsurancePolicy("Test Policy", PolicyStatus.ACTIVE, LocalDateTime.now(), LocalDateTime.now().plusYears(1));
    }

    @Test
    void testFindAll() {
        List<InsurancePolicy> policies = Arrays.asList(policy);
        when(repository.findAll()).thenReturn(policies);

        List<InsurancePolicy> result = service.findAll();
        assertEquals(1, result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        int id = 1;
        when(repository.findById(id)).thenReturn(Optional.of(policy));

        Optional<InsurancePolicy> result = service.findById(id);
        assertTrue(result.isPresent());
        assertEquals("Test Policy", result.get().getName());
        verify(repository, times(1)).findById(id);
    }

    @Test
    void testSave() {
        when(repository.save(any(InsurancePolicy.class))).thenReturn(policy);

        InsurancePolicy result = service.save(policy);
        assertEquals("Test Policy", result.getName());
        verify(repository, times(1)).save(policy);
    }

    @Test
    void testUpdate() {
        when(repository.save(any(InsurancePolicy.class))).thenReturn(policy);

        InsurancePolicy result = service.update(policy);
        assertEquals("Test Policy", result.getName());
        verify(repository, times(1)).save(policy);
    }

    @Test
    void testDeleteById() {
        int id = 1;
        doNothing().when(repository).deleteById(id);

        service.deleteById(id);
        verify(repository, times(1)).deleteById(id);
    }
}
