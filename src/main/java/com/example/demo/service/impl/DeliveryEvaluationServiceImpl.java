package com.example.demo.service.impl;

import com.example.demo.model.DeliveryEvaluation;
import com.example.demo.model.SLARequirement;
import com.example.demo.entiy.Vendor;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.DeliveryEvaluationRepository;
import com.example.demo.repository.SLARequirementRepository;
import com.example.demo.repository.VendorRepository;
import com.example.demo.service.DeliveryEvaluationService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DeliveryEvaluationServiceImpl implements DeliveryEvaluationService {

    private final DeliveryEvaluationRepository evaluationRepository;
    private final VendorRepository vendorRepository;
    private final SLARequirementRepository slaRepository;

    public DeliveryEvaluationServiceImpl(
            DeliveryEvaluationRepository evaluationRepository,
            VendorRepository vendorRepository,
            SLARequirementRepository slaRepository) {
        this.evaluationRepository = evaluationRepository;
        this.vendorRepository = vendorRepository;
        this.slaRepository = slaRepository;
    }

    @Override
    public DeliveryEvaluation createEvaluation(DeliveryEvaluation evaluation) {

        Vendor vendor = vendorRepository.findById(evaluation.getVendor().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Vendor not found"));

        if (!vendor.getActive()) {
            throw new IllegalStateException("Evaluations allowed only for active vendors");
        }

        SLARequirement sla = slaRepository.findById(evaluation.getSlaRequirement().getId())
                .orElseThrow(() -> new ResourceNotFoundException("SLA not found"));

        if (evaluation.getActualDeliveryDays() < 0) {
            throw new IllegalArgumentException("Actual delivery days must be >= 0");
        }

        if (evaluation.getQualityScore() < 0 || evaluation.getQualityScore() > 100) {
            throw new IllegalArgumentException("Quality score must be between 0 and 100");
        }

        evaluation.setVendor(vendor);
        evaluation.setSlaRequirement(sla);
        evaluation.setEvaluationDate(new Date());

        evaluation.setMeetsDeliveryTarget(
                evaluation.getActualDeliveryDays() <= sla.getMaxDeliveryDays());

        evaluation.setMeetsQualityTarget(
                evaluation.getQualityScore() >= sla.getMinQualityScore());

        return evaluationRepository.save(evaluation);
    }

    @Override
    public DeliveryEvaluation getEvaluationById(Long id) {
        return evaluationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evaluation not found"));
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
