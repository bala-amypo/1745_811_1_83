package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.VendorPerformanceScoreService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorPerformanceScoreServiceImpl implements VendorPerformanceScoreService {

    private final VendorPerformanceScoreRepository scoreRepo;
    private final DeliveryEvaluationRepository evaluationRepo;
    private final VendorRepository vendorRepo;
    private final VendorTierRepository tierRepo;

    public VendorPerformanceScoreServiceImpl(VendorPerformanceScoreRepository scoreRepo,
                                             DeliveryEvaluationRepository evaluationRepo,
                                             VendorRepository vendorRepo,
                                             VendorTierRepository tierRepo) {
        this.scoreRepo = scoreRepo;
        this.evaluationRepo = evaluationRepo;
        this.vendorRepo = vendorRepo;
        this.tierRepo = tierRepo;
    }

    @Override
    public VendorPerformanceScore calculateScore(Long vendorId) {
        Vendor vendor = vendorRepo.findById(vendorId)
                .orElseThrow(() -> new IllegalArgumentException("Vendor not found"));

        List<DeliveryEvaluation> evaluations = evaluationRepo.findByVendorId(vendorId);

        double total = evaluations.size();
        double onTime = evaluations.stream().filter(DeliveryEvaluation::getMeetsDeliveryTarget).count();
        double quality = evaluations.stream().filter(DeliveryEvaluation::getMeetsQualityTarget).count();

        double onTimePct = total > 0 ? (onTime / total) * 100 : 0;
        double qualityPct = total > 0 ? (quality / total) * 100 : 0;
        double overall = (onTimePct + qualityPct) / 2;

        VendorPerformanceScore score = new VendorPerformanceScore(vendor, onTimePct, qualityPct, overall);

        List<VendorTier> tiers = tierRepo.findByActiveTrueOrderByMinScoreThresholdDesc();
        tiers.stream()
                .filter(t -> overall >= t.getMinScoreThreshold())
                .findFirst()
                .ifPresent(score::setVendorTier);

        return scoreRepo.save(score);
    }

    @Override
    public VendorPerformanceScore getLatestScore(Long vendorId) {
        List<VendorPerformanceScore> list = scoreRepo.findByVendorOrderByCalculatedAtDesc(vendorId);
        if (list.isEmpty()) return null;
        return list.get(0);
    }

    @Override
    public List<VendorPerformanceScore> getScoresForVendor(Long vendorId) {
        return scoreRepo.findByVendorOrderByCalculatedAtDesc(vendorId);
    }
}
