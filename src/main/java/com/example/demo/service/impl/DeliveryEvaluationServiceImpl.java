package com.example.demo.service.impl;

import com.example.demo.model.DeliveryEvaluation;
import com.example.demo.repository.DeliveryEvaluationRepository;
import com.example.demo.repository.VendorRepository;
import com.example.demo.repository.SLARequirementRepository;
import com.example.demo.service.DeliveryEvaluationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryEvaluationServiceImpl implements DeliveryEvaluationService {

    @Autowired
    private DeliveryEvaluationRepository deliveryEvaluationRepository;

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private SLARequirementRepository slaRequirementRepository;

    @Override
    public List<DeliveryEvaluation> getAllEvaluations() {
        return deliveryEvaluationRepository.findAll();
    }

    @Override
    public DeliveryEvaluation saveEvaluation(DeliveryEvaluation evaluation) {
        return deliveryEvaluationRepository.save(evaluation);
    }

    // Add other service methods as needed
}
