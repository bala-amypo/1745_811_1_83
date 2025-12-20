package com.example.demo.repository;

import com.example.demo.entity.SLARequirement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SLARequirementRepository extends JpaRepository<SLARequirement, Long> {

    boolean existsByRequirementName(String requirementName);
}
