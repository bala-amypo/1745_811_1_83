package com.example.demo.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "vendor_performance_scores")
public class VendorPerformanceScore implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Vendor vendor;

    private Double onTimePercentage;
    private Double qualityCompliancePercentage;
    private Double overallScore;

    @ManyToOne
    private VendorTier vendorTier;

    private LocalDateTime calculatedAt = LocalDateTime.now();

    // Constructors
    public VendorPerformanceScore() {}

    public VendorPerformanceScore(Vendor vendor, Double onTimePercentage, Double qualityCompliancePercentage, Double overallScore) {
        this.vendor = vendor;
        this.onTimePercentage = onTimePercentage;
        this.qualityCompliancePercentage = qualityCompliancePercentage;
        this.overallScore = overallScore;
        this.calculatedAt = LocalDateTime.now();
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Vendor getVendor() { return vendor; }
    public void setVendor(Vendor vendor) { this.vendor = vendor; }

    public Double getOnTimePercentage() { return onTimePercentage; }
    public void setOnTimePercentage(Double onTimePercentage) { this.onTimePercentage = onTimePercentage; }

    public Double getQualityCompliancePercentage() { return qualityCompliancePercentage; }
    public void setQualityCompliancePercentage(Double qualityCompliancePercentage) { this.qualityCompliancePercentage = qualityCompliancePercentage; }

    public Double getOverallScore() { return overallScore; }
    public void setOverallScore(Double overallScore) { this.overallScore = overallScore; }

    public VendorTier getVendorTier() { return vendorTier; }
    public void setVendorTier(VendorTier vendorTier) { this.vendorTier = vendorTier; }

    public LocalDateTime getCalculatedAt() { return calculatedAt; }
    public void setCalculatedAt(LocalDateTime calculatedAt) { this.calculatedAt = calculatedAt; }
}
