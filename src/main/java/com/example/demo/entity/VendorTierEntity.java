package com.example.demo.entity;

public class VendorTierEntity {
    Long id;
    String tierName;
    Double minScoreThreshold;
    String description;
    Boolean active;

}
public VendorTierEntity(Long id,String tierName,Double minScoreThreshold,String description,Boolean active){
    this.id=id;
    this.tierName=tierName;
    this.minScoreThreshold=minScoreThreshold;
    this.description=description;
    this.active=active;
}
public VendorTierEntity(){

}
public Long getId() {
    return id;
}
public void setId(Long id) {
    this.id = id;
}
public String getTierName() {
    return tierName;
}
public void setTierName(String tierName) {
    this.tierName = tierName;
}
public Double getMinScoreThreshold() {
    return minScoreThreshold;
}
public void setMinScoreThreshold(Double minScoreThreshold) {
    this.minScoreThreshold = minScoreThreshold;
}
public String getDescription() {
    return description;
}
public void setDescription(String description) {
    this.description = description;
}
public Boolean getActive() {
    return active;
}
public void setActive(Boolean active) {
    this.active = active;
}
