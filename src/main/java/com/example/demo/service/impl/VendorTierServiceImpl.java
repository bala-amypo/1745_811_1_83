package com.example.demo.service.impl;

import com.example.demo.model.VendorTier;
import com.example.demo.repository.VendorTierRepository;
import com.example.demo.service.VendorTierService;

import java.util.List;

public class VendorTierServiceImpl implements VendorTierService {

    private final VendorTierRepository repository;

    public VendorTierServiceImpl(VendorTierRepository repository) {
        this.repository = repository;
    }

    @Override
    public VendorTier createTier(VendorTier tier) {

        if (tier.getMinScoreThreshold() < 0 || tier.getMinScoreThreshold() > 100) {
            throw new IllegalArgumentException("Score threshold must be between 0–100");
        }

        if (repository.existsByTierName(tier.getTierName())) {
            throw new IllegalArgumentException("Tier name must be unique");
        }

        tier.setActive(true);
        return repository.save(tier);
    }

    @Override
    public VendorTier updateTier(Long id, VendorTier tier) {
        VendorTier existing = repository.findById(id)
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

        return repository.save(existing);
    }

    @Override
    public VendorTier getTierById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vendor tier not found"));
    }

    @Override
    public List<VendorTier> getAllTiers() {
        return repository.findAll();
    }

    @Override
    public void deactivateTier(Long id) {
        VendorTier tier = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vendor tier not found"));
        tier.setActive(false);
        repository.save(tier);
    }
}
