package com.example.demo.service.impl;

import com.example.demo.model.DeliveryEvaluation;
import com.example.demo.model.Vendor;
import com.example.demo.model.VendorPerformanceScore;
import com.example.demo.repository.DeliveryEvaluationRepository;
import com.example.demo.repository.VendorPerformanceScoreRepository;
import com.example.demo.repository.VendorRepository;
import com.example.demo.repository.VendorTierRepository;
import com.example.demo.service.VendorPerformanceScoreService;

import java.time.LocalDateTime;
import java.util.List;

public class VendorPerformanceScoreServiceImpl implements VendorPerformanceScoreService {

    private final VendorPerformanceScoreRepository vendorPerformanceScoreRepository;
    private final DeliveryEvaluationRepository deliveryEvaluationRepository;
    private final VendorRepository vendorRepository;
    private final VendorTierRepository vendorTierRepository;

    public VendorPerformanceScoreServiceImpl(
            VendorPerformanceScoreRepository vendorPerformanceScoreRepository,
            DeliveryEvaluationRepository deliveryEvaluationRepository,
            VendorRepository vendorRepository,
            VendorTierRepository vendorTierRepository) {

        this.vendorPerformanceScoreRepository = vendorPerformanceScoreRepository;
        this.deliveryEvaluationRepository = deliveryEvaluationRepository;
        this.vendorRepository = vendorRepository;
        this.vendorTierRepository = vendorTierRepository;
    }

    @Override
    public VendorPerformanceScore calculateScore(Long vendorId) {

        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new IllegalArgumentException("not found"));

        List<DeliveryEvaluation> evaluations =
                deliveryEvaluationRepository.findByVendorId(vendorId);

        int total = evaluations.size();
        double onTime = 0;
        double quality = 0;

        for (DeliveryEvaluation e : evaluations) {
            if (Boolean.TRUE.equals(e.getMeetsDeliveryTarget())) {
                onTime++;
            }
            if (Boolean.TRUE.equals(e.getMeetsQualityTarget())) {
                quality++;
            }
        }

        double onTimePercentage = total == 0 ? 0 : (onTime / total) * 100;
        double qualityPercentage = total == 0 ? 0 : (quality / total) * 100;
        double overallScore = (onTimePercentage + qualityPercentage) / 2;

        VendorPerformanceScore score = new VendorPerformanceScore(
                vendor,
                onTimePercentage,
                qualityPercentage,
                overallScore
        );

        score.setCalculatedAt(LocalDateTime.now());

        return vendorPerformanceScoreRepository.save(score);
    }

    @Override
    public VendorPerformanceScore getLatestScore(Long vendorId) {
        List<VendorPerformanceScore> list =
                vendorPerformanceScoreRepository.findByVendorOrderByCalculatedAtDesc(vendorId);

        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<VendorPerformanceScore> getScoresForVendor(Long vendorId) {
        return vendorPerformanceScoreRepository.findByVendorOrderByCalculatedAtDesc(vendorId);
    }
}
