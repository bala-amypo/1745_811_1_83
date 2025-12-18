package com.example.demo.repository;

import com.example.demo.entity.VendorTier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendorTierRepository extends JpaRepository<VendorTier, Long> {

    boolean existsByTierName(String tierName);

    List<VendorTier> findByActiveTrueOrderByMinScoreThresholdDesc();
}
