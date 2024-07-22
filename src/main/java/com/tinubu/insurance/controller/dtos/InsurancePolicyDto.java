package com.tinubu.insurance.controller.dtos;

import com.tinubu.insurance.model.InsurancePolicy;
import com.tinubu.insurance.model.PolicyStatus;

import java.time.LocalDateTime;

public record InsurancePolicyDto(int id,
                                 String name,
                                 PolicyStatus status,
                                 LocalDateTime startDate,
                                 LocalDateTime endDate,
                                 LocalDateTime creationDate,
                                 LocalDateTime updateDate) {

    public static InsurancePolicyDto fromEntity(InsurancePolicy policy) {
        return new InsurancePolicyDto(
                policy.getId(),
                policy.getName(),
                policy.getStatus(),
                policy.getStartDate(),
                policy.getEndDate(),
                policy.getCreatedAt(),
                policy.getUpdatedAt()
        );
    }

    public InsurancePolicy toEntity() {
        InsurancePolicy policy = new InsurancePolicy();
        policy.setId(this.id);
        policy.setName(this.name);
        policy.setStatus(this.status);
        policy.setStartDate(this.startDate);
        policy.setEndDate(this.endDate);
        return policy;
    }
}
