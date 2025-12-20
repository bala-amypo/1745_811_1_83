package com.example.demo.controller;

import com.example.demo.entity.DeliveryEvaluation;
import com.example.demo.service.DeliveryEvaluationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evaluations")
public class DeliveryEvaluationController {

    private final DeliveryEvaluationService deliveryEvaluationService;

    public DeliveryEvaluationController(DeliveryEvaluationService deliveryEvaluationService) {
        this.deliveryEvaluationService = deliveryEvaluationService;
    }

    @PostMapping
    public DeliveryEvaluation createEvaluation(
            @RequestBody DeliveryEvaluation evaluation) {

        return deliveryEvaluationService.createEvaluation(evaluation);
    }

    @GetMapping("/{id}")
    public DeliveryEvaluation getEvaluationById(@PathVariable Long id) {
        return deliveryEvaluationService.getEvaluationById(id);
    }

    @GetMapping("/vendor/{vendorId}")
    public List<DeliveryEvaluation> getByVendor(
            @PathVariable Long vendorId) {

        return deliveryEvaluationService.getEvaluationsForVendor(vendorId);
    }

    @GetMapping("/requirement/{reqId}")
    public List<DeliveryEvaluation> getByRequirement(
            @PathVariable Long reqId) {

        return deliveryEvaluationService.getEvaluationsForRequirement(reqId);
    }
}
