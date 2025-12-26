package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class VendorPerformanceScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Vendor vendor;

    private Double onTimePercentage;
    private Double qualityCompliancePercentage;
    private Double overallScore;
    private LocalDateTime calculatedAt = LocalDateTime.now();

    public VendorPerformanceScore() {}

    public VendorPerformanceScore(Vendor v, Double o, Double q, Double overall) {
        this.vendor = v;
        this.onTimePercentage = o;
        this.qualityCompliancePercentage = q;
        this.overallScore = overall;
    }

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

    public LocalDateTime getCalculatedAt() { return calculatedAt; }
    public void setCalculatedAt(LocalDateTime calculatedAt) { this.calculatedAt = calculatedAt; }
}
