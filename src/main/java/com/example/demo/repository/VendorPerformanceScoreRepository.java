package com.example.demo.repository;

import com.example.demo.model.Vendor;
import com.example.demo.model.VendorPerformanceScore;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VendorPerformanceScoreRepository extends JpaRepository<VendorPerformanceScore, Long> {

    List<VendorPerformanceScore> findByVendorOrderByCalculatedAtDesc(Vendor vendor);
}
