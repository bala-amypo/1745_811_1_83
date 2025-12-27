package com.example.demo.service.impl;

import com.example.demo.model.DeliveryEvaluation;
import com.example.demo.model.SLARequirement;
import com.example.demo.model.Vendor;
import com.example.demo.repository.DeliveryEvaluationRepository;
import com.example.demo.repository.SLARequirementRepository;
import com.example.demo.repository.VendorRepository;
import com.example.demo.service.DeliveryEvaluationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryEvaluationServiceImpl implements DeliveryEvaluationService {

    private final DeliveryEvaluationRepository evaluationRepository;
    private final VendorRepository vendorRepository;
    private final SLARequirementRepository slaRepository;

    public DeliveryEvaluationServiceImpl(DeliveryEvaluationRepository evaluationRepository,
                                         VendorRepository vendorRepository,
                                         SLARequirementRepository slaRepository) {
        this.evaluationRepository = evaluationRepository;
        this.vendorRepository = vendorRepository;
        this.slaRepository = slaRepository;
    }

    @Override
    public DeliveryEvaluation createEvaluation(DeliveryEvaluation eval) {
        Vendor v = vendorRepository.findById(eval.getVendor().getId())
                .orElseThrow(() -> new IllegalArgumentException("Vendor not found"));
        SLARequirement sla = slaRepository.findById(eval.getSlaRequirement().getId())
                .orElseThrow(() -> new IllegalArgumentException("SLA Requirement not found"));

        if (!Boolean.TRUE.equals(v.getActive()))
            throw new IllegalStateException("Cannot evaluate inactive vendors");

        if (eval.getActualDeliveryDays() < 0)
            throw new IllegalArgumentException("Actual delivery days must be >= 0");

        if (eval.getQualityScore() < 0 || eval.getQualityScore() > 100)
            throw new IllegalArgumentException("Quality score must be between 0 and 100");

        eval.setMeetsDeliveryTarget(eval.getActualDeliveryDays() <= sla.getMaxDeliveryDays());
        eval.setMeetsQualityTarget(eval.getQualityScore() >= sla.getQualityScoreThreshold());

        return evaluationRepository.save(eval);
    }

    @Override
    public List<DeliveryEvaluation> getEvaluationsForVendor(Long vendorId) {
        return evaluationRepository.findByVendorId(vendorId);
    }

    @Override
    public List<DeliveryEvaluation> getEvaluationsForRequirement(Long requirementId) {
        return evaluationRepository.findBySlaRequirementId(requirementId);
    }
}
