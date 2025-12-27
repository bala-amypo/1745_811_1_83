package com.example.demo.service.impl;

import com.example.demo.model.DeliveryEvaluation;
import com.example.demo.repository.DeliveryEvaluationRepository;
import com.example.demo.service.DeliveryEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryEvaluationServiceImpl implements DeliveryEvaluationService {

    @Autowired
    private DeliveryEvaluationRepository deliveryEvaluationRepository;

    @Override
    public List<DeliveryEvaluation> getEvaluationsForRequirement(Long requirementId) {
        return deliveryEvaluationRepository.findByRequirementId(requirementId);
    }

    @Override
    public List<DeliveryEvaluation> getEvaluationsForVendor(Long vendorId) {
        return deliveryEvaluationRepository.findByVendorId(vendorId);
    }
}
