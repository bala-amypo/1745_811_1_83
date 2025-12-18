package com.example.demo.controller;

import com.example.demo.model.VendorTier;
import com.example.demo.service.VendorTierService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tiers")
public class VendorTierController {

    private final VendorTierService vendorTierService;

    public VendorTierController(VendorTierService vendorTierService) {
        this.vendorTierService = vendorTierService;
    }

    @PostMapping
    public VendorTier createTier(@RequestBody VendorTier tier) {
        return vendorTierService.createTier(tier);
    }

    @PutMapping("/{id}")
    public VendorTier updateTier(
            @PathVariable Long id,
            @RequestBody VendorTier tier) {

        return vendorTierService.updateTier(id, tier);
    }

    @GetMapping("/{id}")
    public VendorTier getTier(@PathVariable Long id) {
        return vendorTierService.getTierById(id);
    }

    @GetMapping
    public List<VendorTier> getAllTiers() {
        return vendorTierService.getAllTiers();
    }

    @PutMapping("/{id}/deactivate")
    public void deactivateTier(@PathVariable Long id) {
        vendorTierService.deactivateTier(id);
    }
}
