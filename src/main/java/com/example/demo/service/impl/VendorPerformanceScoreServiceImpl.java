package com.example.demo.service.impl;

import com.example.demo.model.DeliveryEvaluation;
import com.example.demo.model.Vendor;
import com.example.demo.model.VendorPerformanceScore;
import com.example.demo.model.VendorTier;
import com.example.demo.repository.DeliveryEvaluationRepository;
import com.example.demo.repository.VendorPerformanceScoreRepository;
import com.example.demo.repository.VendorRepository;
import com.example.demo.repository.VendorTierRepository;
import com.example.demo.service.VendorPerformanceScoreService;

import java.util.List;

public class VendorPerformanceScoreServiceImpl implements VendorPerformanceScoreService {

    private final VendorPerformanceScoreRepository scoreRepository;
    private final DeliveryEvaluationRepository evaluationRepository;
    private final VendorRepository vendorRepository;
    private final VendorTierRepository vendorTierRepository;

    public VendorPerformanceScoreServiceImpl(
            VendorPerformanceScoreRepository scoreRepository,
            DeliveryEvaluationRepository evaluationRepository,
            VendorRepository vendorRepository,
            VendorTierRepository vendorTierRepository) {

        this.scoreRepository = scoreRepository;
        this.evaluationRepository = evaluationRepository;
        this.vendorRepository = vendorRepository;
        this.vendorTierRepository = vendorTierRepository;
    }

    @Override
    public VendorPerformanceScore calculateScore(Long vendorId) {

        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new IllegalArgumentException("Vendor not found"));

        List<DeliveryEvaluation> evaluations =
                evaluationRepository.findByVendorId(vendorId);

        if (evaluations.isEmpty()) {
            VendorPerformanceScore emptyScore =
                    new VendorPerformanceScore(vendor, 0, 0, 0);
            return scoreRepository.save(emptyScore);
        }

        long onTimeCount = evaluations.stream()
                .filter(DeliveryEvaluation::getMeetsDeliveryTarget)
                .count();

        long qualityCount = evaluations.stream()
                .filter(DeliveryEvaluation::getMeetsQualityTarget)
                .count();

        double onTimePercent =
                (onTimeCount * 100.0) / evaluations.size();

        double qualityPercent =
                (qualityCount * 100.0) / evaluations.size();

        double overall =
                (onTimePercent + qualityPercent) / 2;

        VendorPerformanceScore score =
                new VendorPerformanceScore(vendor,
                        onTimePercent,
                        qualityPercent,
                        overall);

        List<VendorTier> tiers =
                vendorTierRepository.findByActiveTrueOrderByMinScoreThresholdDesc();

        for (VendorTier tier : tiers) {
            if (overall >= tier.getMinScoreThreshold()) {
                score.setVendorTier(tier);
                break;
            }
        }

        return scoreRepository.save(score);
    }

    @Override
    public VendorPerformanceScore getLatestScore(Long vendorId) {
        List<VendorPerformanceScore> list =
                scoreRepository.findByVendorOrderByCalculatedAtDesc(vendorId);

        if (list.isEmpty()) {
            throw new IllegalArgumentException("No performance score found");
        }
        return list.get(0);
    }

    @Override
    public List<VendorPerformanceScore> getScoresForVendor(Long vendorId) {
        return scoreRepository.findByVendorOrderByCalculatedAtDesc(vendorId);
    }
}
