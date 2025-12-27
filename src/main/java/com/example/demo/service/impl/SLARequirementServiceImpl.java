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
    public List<SLARequirement> getAllSLARequirements() {
        return slaRequirementRepository.findAll();
    }

    @Override
    public SLARequirement saveSLARequirement(SLARequirement slaRequirement) {
        return slaRequirementRepository.save(slaRequirement);
    }

    // Add other service methods as needed
}
