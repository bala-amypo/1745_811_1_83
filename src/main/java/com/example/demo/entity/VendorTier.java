package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "vendor_tiers")
public class VendorTier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String tierName;

    @Column(nullable = false)
    private Integer minScoreThreshold;

    @Column(nullable = false)
    private Boolean active = true;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public VendorTier() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTierName() {
        return tierName;
    }

    public void setTierName(String tierName) {
        this.tierName = tierName;
    }

    public Integer getMinScoreThreshold() {
        return minScoreThreshold;
    }

    public void setMinScoreThreshold(Integer minScoreThreshold) {
        this.minScoreThreshold = minScoreThreshold;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
