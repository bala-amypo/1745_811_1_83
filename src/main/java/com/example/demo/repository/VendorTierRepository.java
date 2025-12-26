package com.example.demo.repository;

import com.example.demo.model.VendorTier;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VendorTierRepository extends JpaRepository<VendorTier, Long> {

    boolean existsByTierName(String name);

    List<VendorTier> findByActiveTrueOrderByMinScoreThresholdDesc();
}
