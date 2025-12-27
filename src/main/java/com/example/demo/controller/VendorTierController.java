package com.example.demo.controller;

import com.example.demo.model.VendorTier;
import com.example.demo.service.VendorTierService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tiers")
public class VendorTierController {

    private final VendorTierService service;

    public VendorTierController(VendorTierService service) {
        this.service = service;
    }

    @PostMapping
    public VendorTier create(@RequestBody VendorTier tier) {
        return service.createTier(tier);
    }

    @PutMapping("/{id}/deactivate")
    public void deactivate(@PathVariable Long id) {
        service.deactivateTier(id);
    }

    @GetMapping
    public List<VendorTier> getAll() {
        return service.getAllTiers();
    }
}
