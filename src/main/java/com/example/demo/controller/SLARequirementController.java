package com.example.demo.entity;


import jakarta.persistence.*;


@Entity
@Table(name = "sla_requirements")
public class SLARequirement {


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;


@Column(unique = true, nullable = false)
private String requirementName;


public Long getId() {
    return id;
}
public void setId(Long id) {
    this.id = id;
}
public String getRequirementName() {
    return requirementName;
}
public void setRequirementName(String requirementName) {
    this.requirementName = requirementName;
}
public String getDescription() {
    return description;
}
public void setDescription(String description) {
    this.description = description;
}
public Integer getMaxDeliveryDays() {
    return maxDeliveryDays;
}
public void setMaxDeliveryDays(Integer maxDeliveryDays) {
    this.maxDeliveryDays = maxDeliveryDays;
}
public Double getMinQualityScore() {
    return minQualityScore;
}
public void setMinQualityScore(Double minQualityScore) {
    this.minQualityScore = minQualityScore;
}
public Boolean getActive() {
    return active;
}
public void setActive(Boolean active) {
    this.active = active;
}
private String description;
private Integer maxDeliveryDays;
private Double minQualityScore;
private Boolean active = true;



}