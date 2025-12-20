package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "vendor_tiers")
public class VendorTier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tierName;
    private String description;
    private Double minScoreThreshold;
    private Boolean active = true;

    public VendorTier() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTierName() { return tierName; }
    public void setTierName(String tierName) { this.tierName = tierName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Double getMinScoreThreshold() { return minScoreThreshold; }
    public void setMinScoreThreshold(Double minScoreThreshold) { this.minScoreThreshold = minScoreThreshold; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
