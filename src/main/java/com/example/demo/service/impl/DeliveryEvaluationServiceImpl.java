package com.example.demo.service.impl;

import com.example.demo.model.DeliveryEvaluation;
import com.example.demo.model.SLARequirement;
import com.example.demo.model.Vendor;
import com.example.demo.repository.DeliveryEvaluationRepository;
import com.example.demo.repository.SLARequirementRepository;
import com.example.demo.repository.VendorRepository;
import com.example.demo.service.DeliveryEvaluationService;

import java.util.List;

public class DeliveryEvaluationServiceImpl implements DeliveryEvaluationService {

    private final DeliveryEvaluationRepository deliveryEvaluationRepository;
    private final VendorRepository vendorRepository;
    private final SLARequirementRepository slaRequirementRepository;

    public DeliveryEvaluationServiceImpl(DeliveryEvaluationRepository deliveryEvaluationRepository,
                                         VendorRepository vendorRepository,
                                         SLARequirementRepository slaRequirementRepository) {
        this.deliveryEvaluationRepository = deliveryEvaluationRepository;
        this.vendorRepository = vendorRepository;
        this.slaRequirementRepository = slaRequirementRepository;
    }

    @Override
    public DeliveryEvaluation createEvaluation(DeliveryEvaluation evaluation) {

        Vendor vendor = vendorRepository.findById(evaluation.getVendor().getId())
                .orElseThrow(() -> new IllegalArgumentException("Vendor not found"));

        if (!vendor.getActive()) {
            throw new IllegalStateException("Evaluations allowed only for active vendors");
        }

        SLARequirement sla = slaRequirementRepository
                .findById(evaluation.getSlaRequirement().getId())
                .orElseThrow(() -> new IllegalArgumentException("SLA requirement not found"));

        if (evaluation.getActualDeliveryDays() < 0) {
            throw new IllegalArgumentException("Actual delivery days must be >= 0");
        }

        if (evaluation.getQualityScore() < 0 || evaluation.getQualityScore() > 100) {
            throw new IllegalArgumentException("Quality score must be between 0 and 100");
        }

        evaluation.setMeetsDeliveryTarget(
                evaluation.getActualDeliveryDays() <= sla.getMaxDeliveryDays());

        evaluation.setMeetsQualityTarget(
                evaluation.getQualityScore() >= sla.getQualityScoreThreshold());

        return deliveryEvaluationRepository.save(evaluation);
    }

    @Override
    public DeliveryEvaluation getEvaluationById(Long id) {
        return deliveryEvaluationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Delivery evaluation not found"));
    }

    @Override
    public List<DeliveryEvaluation> getEvaluationsForVendor(Long vendorId) {
        return deliveryEvaluationRepository.findByVendorId(vendorId);
    }

    @Override
    public List<DeliveryEvaluation> getEvaluationsForRequirement(Long requirementId) {
        return deliveryEvaluationRepository.findBySlaRequirementId(requirementId);
    }
}
