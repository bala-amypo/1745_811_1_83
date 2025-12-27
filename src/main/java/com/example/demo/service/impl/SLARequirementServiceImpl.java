package com.example.demo.service.impl;

import com.example.demo.model.SLARequirement;
import com.example.demo.repository.SLARequirementRepository;
import com.example.demo.service.SLARequirementService;

import java.util.List;

public class SLARequirementServiceImpl implements SLARequirementService {

    private final SLARequirementRepository slaRequirementRepository;

    public SLARequirementServiceImpl(SLARequirementRepository slaRequirementRepository) {
        this.slaRequirementRepository = slaRequirementRepository;
    }

    @Override
    public SLARequirement createRequirement(SLARequirement requirement) {

        if (requirement.getMaxDeliveryDays() == null || requirement.getMaxDeliveryDays() <= 0) {
            throw new IllegalArgumentException("Max delivery days must be greater than 0");
        }

        if (requirement.getQualityScoreThreshold() < 0 ||
                requirement.getQualityScoreThreshold() > 100) {
            throw new IllegalArgumentException("Quality score threshold must be between 0 and 100");
        }

        if (slaRequirementRepository.existsByRequirementName(requirement.getRequirementName())) {
            throw new IllegalArgumentException("SLA requirement name must be unique");
        }

        requirement.setActive(true);
        return slaRequirementRepository.save(requirement);
    }

    @Override
    public SLARequirement updateRequirement(Long id, SLARequirement requirement) {
        SLARequirement existing = slaRequirementRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("SLA requirement not found"));

        if (requirement.getRequirementName() != null &&
                !requirement.getRequirementName().equals(existing.getRequirementName()) &&
                slaRequirementRepository.existsByRequirementName(requirement.getRequirementName())) {
            throw new IllegalArgumentException("SLA requirement name must be unique");
        }

        if (requirement.getRequirementName() != null) {
            existing.setRequirementName(requirement.getRequirementName());
        }
        if (requirement.getDescription() != null) {
            existing.setDescription(requirement.getDescription());
        }
        if (requirement.getMaxDeliveryDays() != null) {
            existing.setMaxDeliveryDays(requirement.getMaxDeliveryDays());
        }
        if (requirement.getQualityScoreThreshold() != null) {
            existing.setQualityScoreThreshold(requirement.getQualityScoreThreshold());
        }

        return slaRequirementRepository.save(existing);
    }

    @Override
    public SLARequirement getRequirementById(Long id) {
        return slaRequirementRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("SLA requirement not found"));
    }

    @Override
    public List<SLARequirement> getAllRequirements() {
        return slaRequirementRepository.findAll();
    }

    @Override
    public void deactivateRequirement(Long id) {
        SLARequirement req = slaRequirementRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("SLA requirement not found"));
        req.setActive(false);
        slaRequirementRepository.save(req);
    }
}
