package com.example.demo.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "delivery_evaluations")
public class DeliveryEvaluation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Vendor vendor;

    @ManyToOne
    private SLARequirement slaRequirement;

    private Integer actualDeliveryDays;
    private Double qualityScore;

    private Boolean meetsDeliveryTarget;
    private Boolean meetsQualityTarget;

    private LocalDate evaluationDate;

    // Constructors
    public DeliveryEvaluation() {}

    public DeliveryEvaluation(Vendor vendor, SLARequirement slaRequirement, Integer actualDeliveryDays, Double qualityScore, LocalDate evaluationDate) {
        this.vendor = vendor;
        this.slaRequirement = slaRequirement;
        this.actualDeliveryDays = actualDeliveryDays;
        this.qualityScore = qualityScore;
        this.evaluationDate = evaluationDate;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Vendor getVendor() { return vendor; }
    public void setVendor(Vendor vendor) { this.vendor = vendor; }

    public SLARequirement getSlaRequirement() { return slaRequirement; }
    public void setSlaRequirement(SLARequirement slaRequirement) { this.slaRequirement = slaRequirement; }

    public Integer getActualDeliveryDays() { return actualDeliveryDays; }
    public void setActualDeliveryDays(Integer actualDeliveryDays) { this.actualDeliveryDays = actualDeliveryDays; }

    public Double getQualityScore() { return qualityScore; }
    public void setQualityScore(Double qualityScore) { this.qualityScore = qualityScore; }

    public Boolean getMeetsDeliveryTarget() { return meetsDeliveryTarget; }
    public void setMeetsDeliveryTarget(Boolean meetsDeliveryTarget) { this.meetsDeliveryTarget = meetsDeliveryTarget; }

    public Boolean getMeetsQualityTarget() { return meetsQualityTarget; }
    public void setMeetsQualityTarget(Boolean meetsQualityTarget) { this.meetsQualityTarget = meetsQualityTarget; }

    public LocalDate getEvaluationDate() { return evaluationDate; }
    public void setEvaluationDate(LocalDate evaluationDate) { this.evaluationDate = evaluationDate; }
}
