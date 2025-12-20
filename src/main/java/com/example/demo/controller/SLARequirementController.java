package com.example.demo.controller;

import com.example.demo.entity.SLARequirement;
import com.example.demo.service.SLARequirementService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sla-requirements")
public class SLARequirementController {

    private final SLARequirementService slaRequirementService;

    public SLARequirementController(SLARequirementService slaRequirementService) {
        this.slaRequirementService = slaRequirementService;
    }

    @PostMapping
    public SLARequirement createRequirement(@RequestBody SLARequirement req) {
        return slaRequirementService.createRequirement(req);
    }

    @PutMapping("/{id}")
    public SLARequirement updateRequirement(
            @PathVariable Long id,
            @RequestBody SLARequirement req) {

        return slaRequirementService.updateRequirement(id, req);
    }

    @GetMapping("/{id}")
    public SLARequirement getRequirement(@PathVariable Long id) {
        return slaRequirementService.getRequirementById(id);
    }

    @GetMapping
    public List<SLARequirement> getAllRequirements() {
        return slaRequirementService.getAllRequirements();
    }

    @PutMapping("/{id}/deactivate")
    public void deactivateRequirement(@PathVariable Long id) {
        slaRequirementService.deactivateRequirement(id);
    }
}
