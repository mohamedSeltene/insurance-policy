package com.tinubu.insurance.controller.dtos;

import com.tinubu.insurance.model.InsurancePolicy;
import com.tinubu.insurance.model.PolicyStatus;

import java.time.LocalDateTime;

public record InsurancePolicyCreationDto(String name,
                                         PolicyStatus status,
                                         LocalDateTime startDate,
                                         LocalDateTime endDate) {
    public InsurancePolicy toEntity() {
        InsurancePolicy policy = new InsurancePolicy();
        policy.setName(this.name);
        policy.setStatus(this.status);
        policy.setStartDate(this.startDate);
        policy.setEndDate(this.endDate);
        return policy;
    }
}
