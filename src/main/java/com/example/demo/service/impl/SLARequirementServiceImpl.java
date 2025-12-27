package com.example.demo.service.impl;

import com.example.demo.model.SLARequirement;
import com.example.demo.repository.SLARequirementRepository;
import com.example.demo.service.SLARequirementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SLARequirementServiceImpl implements SLARequirementService {

    @Autowired
    private SLARequirementRepository slaRequirementRepository;

    @Override
    public List<SLARequirement> getAllRequirements() {
        return slaRequirementRepository.findAll();
    }

    @Override
    public void deactivateRequirement(Long requirementId) {
        SLARequirement requirement = slaRequirementRepository.findById(requirementId)
                .orElseThrow(() -> new RuntimeException("Requirement not found"));
        requirement.setActive(false);
        slaRequirementRepository.save(requirement);
    }
}
