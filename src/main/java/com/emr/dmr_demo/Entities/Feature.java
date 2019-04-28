package com.emr.dmr_demo.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
public class Feature {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long featureId;
    private String name;
    private FeatureType type;
    private double result;
    @ManyToOne
    @JoinColumn(name = "patientId")
    @JsonIgnoreProperties("featureList")
    private Patient patient;

    public Feature() {
    }

    public Feature(String name, FeatureType type, double result) {
        this.name = name;
        this.type = type;
        this.result = result;
    }

    public long getFeatureId() {
        return featureId;
    }

    public void setFeatureId(long featureId) {
        this.featureId = featureId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FeatureType getType() {
        return type;
    }

    public void setType(FeatureType type) {
        this.type = type;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
