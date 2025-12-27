package com.example.demo.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "sla_requirements")
public class SLARequirement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String requirementName;

    private String description;

    private Integer maxDeliveryDays;

    private Double qualityScoreThreshold;

    private Boolean active = true;

    // Constructors
    public SLARequirement() {}

    public SLARequirement(String requirementName, String description, Integer maxDeliveryDays, Double qualityScoreThreshold) {
        this.requirementName = requirementName;
        this.description = description;
        this.maxDeliveryDays = maxDeliveryDays;
        this.qualityScoreThreshold = qualityScoreThreshold;
        this.active = true;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRequirementName() { return requirementName; }
    public void setRequirementName(String requirementName) { this.requirementName = requirementName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Integer getMaxDeliveryDays() { return maxDeliveryDays; }
    public void setMaxDeliveryDays(Integer maxDeliveryDays) { this.maxDeliveryDays = maxDeliveryDays; }

    public Double getQualityScoreThreshold() { return qualityScoreThreshold; }
    public void setQualityScoreThreshold(Double qualityScoreThreshold) { this.qualityScoreThreshold = qualityScoreThreshold; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
