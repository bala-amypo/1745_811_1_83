package com.example.demo.service.impl;

import com.example.demo.model.VendorTier;
import com.example.demo.repository.VendorTierRepository;
import com.example.demo.service.VendorTierService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service  // <-- Makes this class a Spring-managed service
public class VendorTierServiceImpl implements VendorTierService {

    private final VendorTierRepository vendorTierRepository;

    public VendorTierServiceImpl(VendorTierRepository vendorTierRepository) {
        this.vendorTierRepository = vendorTierRepository;
    }

    @Override
    public VendorTier createTier(VendorTier tier) {
        if (tier.getMinScoreThreshold() < 0 || tier.getMinScoreThreshold() > 100) {
            throw new IllegalArgumentException("Score threshold must be between 0–100");
        }

        if (vendorTierRepository.existsByTierName(tier.getTierName())) {
            throw new IllegalArgumentException("Vendor tier name must be unique");
        }

        tier.setActive(true);
        return vendorTierRepository.save(tier);
    }

    @Override
    public VendorTier updateTier(Long id, VendorTier tier) {
        VendorTier existing = vendorTierRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vendor tier not found"));

        if (tier.getTierName() != null) {
            existing.setTierName(tier.getTierName());
        }
        if (tier.getMinScoreThreshold() != null) {
            existing.setMinScoreThreshold(tier.getMinScoreThreshold());
        }
        if (tier.getDescription() != null) {
            existing.setDescription(tier.getDescription());
        }

        return vendorTierRepository.save(existing);
    }

    @Override
    public VendorTier getTierById(Long id) {
        return vendorTierRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vendor tier not found"));
    }

    @Override
    public List<VendorTier> getAllTiers() {
        return vendorTierRepository.findAll();
    }

    @Override
    public void deactivateTier(Long id) {
        VendorTier tier = vendorTierRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vendor tier not found"));
        tier.setActive(false);
        vendorTierRepository.save(tier);
    }
}
