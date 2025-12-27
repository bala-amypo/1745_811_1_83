package com.example.demo.controller;

import com.example.demo.model.SLARequirement;
import com.example.demo.service.SLARequirementService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/sla")
public class SLARequirementController {

    private final SLARequirementService service;

    public SLARequirementController(SLARequirementService service) {
        this.service = service;
    }

    @PostMapping
    public SLARequirement create(@RequestBody SLARequirement req) {
        return service.createRequirement(req);
    }

    @PutMapping("/{id}")
    public SLARequirement update(@PathVariable Long id, @RequestBody SLARequirement req) {
        return service.updateRequirement(id, req);
    }

    @GetMapping("/{id}")
    public SLARequirement getById(@PathVariable Long id) {
        return service.getRequirementById(id);
    }

    @GetMapping
    public List<SLARequirement> getAll() {
        return service.getAllRequirements();
    }

    @DeleteMapping("/{id}")
    public void deactivate(@PathVariable Long id) {
        service.deactivateRequirement(id);
    }
}
