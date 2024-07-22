package com.tinubu.insurance.controller;

import com.tinubu.insurance.controller.dtos.InsurancePolicyCreationDto;
import com.tinubu.insurance.controller.dtos.InsurancePolicyDto;
import com.tinubu.insurance.model.InsurancePolicy;
import com.tinubu.insurance.service.InsurancePolicyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/policies")
public class RestInsurancePolicyController {
    private final InsurancePolicyService service;

    public RestInsurancePolicyController(InsurancePolicyService service) {
        this.service = service;
    }

    @GetMapping
    public List<InsurancePolicyDto> getAllPolicies() {
        return service.findAll().stream()
                .map(InsurancePolicyDto::fromEntity)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InsurancePolicyDto> getPolicyById(@PathVariable int id) {
        Optional<InsurancePolicy> policy = service.findById(id);
        return policy.map(value -> ResponseEntity.ok(InsurancePolicyDto.fromEntity(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public InsurancePolicyDto createPolicy(@RequestBody InsurancePolicyCreationDto policyDTO) {
        InsurancePolicy policy = policyDTO.toEntity();
        policy.setCreatedAt(LocalDateTime.now());
        policy.setUpdatedAt(LocalDateTime.now());
        InsurancePolicy savedPolicy = service.save(policy);
        return InsurancePolicyDto.fromEntity(savedPolicy);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InsurancePolicyDto> updatePolicy(@PathVariable int id,
                                                           @RequestBody InsurancePolicyCreationDto policyDetails) {
        Optional<InsurancePolicy> optionalPolicy = service.findById(id);
        if (optionalPolicy.isPresent()) {
            optionalPolicy.get().setName(policyDetails.name());
            optionalPolicy.get().setStatus(policyDetails.status());
            optionalPolicy.get().setStartDate(policyDetails.startDate());
            optionalPolicy.get().setEndDate(policyDetails.endDate());
            optionalPolicy.get().setUpdatedAt(LocalDateTime.now());
            InsurancePolicy updatedPolicy = service.update(optionalPolicy.get());
            return ResponseEntity.ok(InsurancePolicyDto.fromEntity(updatedPolicy));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePolicy(@PathVariable int id) {
        Optional<InsurancePolicy> optionalPolicy = service.findById(id);
        if(optionalPolicy.isPresent()) {
            service.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
