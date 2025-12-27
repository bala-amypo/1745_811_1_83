package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "vendor_performance_scores")
public class VendorPerformanceScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Vendor vendor;

    private Double onTimePercentage;

    private Double qualityCompliancePercentage;

    private Double overallScore;

    private LocalDateTime calculatedAt;

    public VendorPerformanceScore() {
    }

    public VendorPerformanceScore(Vendor vendor, Double onTimePercentage,
                                  Double qualityCompliancePercentage,
                                  Double overallScore) {
        this.vendor = vendor;
        this.onTimePercentage = onTimePercentage;
        this.qualityCompliancePercentage = qualityCompliancePercentage;
        this.overallScore = overallScore;
        this.calculatedAt = LocalDateTime.now();
    }

    // Getters & Setters

    public Long getId() {
        return id;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public Double getOnTimePercentage() {
        return onTimePercentage;
    }

    public Double getQualityCompliancePercentage() {
        return qualityCompliancePercentage;
    }

    public Double getOverallScore() {
        return overallScore;
    }

    public LocalDateTime getCalculatedAt() {
        return calculatedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public void setOnTimePercentage(Double onTimePercentage) {
        this.onTimePercentage = onTimePercentage;
    }

    public void setQualityCompliancePercentage(Double qualityCompliancePercentage) {
        this.qualityCompliancePercentage = qualityCompliancePercentage;
    }

    public void setOverallScore(Double overallScore) {
        this.overallScore = overallScore;
    }

    public void setCalculatedAt(LocalDateTime calculatedAt) {
        this.calculatedAt = calculatedAt;
    }
}
