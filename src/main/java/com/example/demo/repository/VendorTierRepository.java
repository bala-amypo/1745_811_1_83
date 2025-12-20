package com.example.demo.repository;

import com.example.demo.entity.VendorTier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VendorTierRepository extends JpaRepository<VendorTier, Long> {

    List<VendorTier> findByActiveTrueOrderByMinScoreThresholdDesc();

    boolean existsByTierName(String tierName);
}
