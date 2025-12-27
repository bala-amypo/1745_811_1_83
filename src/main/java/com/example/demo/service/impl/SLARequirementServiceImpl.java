package com.example.demo.service.impl;

import com.example.demo.model.SLARequirement;
import com.example.demo.repository.SLARequirementRepository;
import com.example.demo.service.SLARequirementService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SLARequirementServiceImpl implements SLARequirementService {

    private final SLARequirementRepository repository;

    public SLARequirementServiceImpl(SLARequirementRepository repository) {
        this.repository = repository;
    }

    @Override
    public SLARequirement createRequirement(SLARequirement requirement) {
        if (requirement.getMaxDeliveryDays() <= 0)
            throw new IllegalArgumentException("Max delivery days must be > 0");
        if (requirement.getQualityScoreThreshold() < 0 || requirement.getQualityScoreThreshold() > 100)
            throw new IllegalArgumentException("Quality score must be 0–100");

        if (repository.existsByRequirementName(requirement.getRequirementName()))
            throw new IllegalArgumentException("Requirement name must be unique");

        return repository.save(requirement);
    }

    @Override
    public SLARequirement updateRequirement(Long id, SLARequirement requirement) {
        SLARequirement existing = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("SLA Requirement not found"));

        if (requirement.getRequirementName() != null &&
            repository.existsByRequirementName(requirement.getRequirementName()))
            throw new IllegalArgumentException("Requirement name must be unique");

        if (requirement.getRequirementName() != null) existing.setRequirementName(requirement.getRequirementName());
        if (requirement.getDescription() != null) existing.setDescription(requirement.getDescription());
        if (requirement.getMaxDeliveryDays() != null) existing.setMaxDeliveryDays(requirement.getMaxDeliveryDays());
        if (requirement.getQualityScoreThreshold() != null) existing.setQualityScoreThreshold(requirement.getQualityScoreThreshold());

        return repository.save(existing);
    }

    @Override
    public SLARequirement getRequirementById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("SLA Requirement not found"));
    }

    @Override
    public List<SLARequirement> getAllRequirements() {
        return repository.findAll();
    }

    @Override
    public void deactivateRequirement(Long id) {
        SLARequirement existing = getRequirementById(id);
        existing.setActive(false);
        repository.save(existing);
    }
}
