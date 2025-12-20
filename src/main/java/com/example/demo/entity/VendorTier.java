package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "vendor_tiers", uniqueConstraints = @UniqueConstraint(columnNames = "tierName"))
public class VendorTier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String tierName;

    private Double minScoreThreshold;

    private String description;

    @Column(nullable = false)
    private Boolean active = true;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTierName() { return tierName; }
    public void setTierName(String tierName) { this.tierName = tierName; }

    public Double getMinScoreThreshold() { return minScoreThreshold; }
    public void setMinScoreThreshold(Double minScoreThreshold) { this.minScoreThreshold; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
