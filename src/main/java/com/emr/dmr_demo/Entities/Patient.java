package com.emr.dmr_demo.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long patientId;
    private String name;
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("patient")
    private List<Feature> featureList;
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("patient")
    private List<Disease> diseaseList;

    public Patient() {
    }

    public Patient(String name) {
        this.name = name;
        this.featureList = new LinkedList<>();
        this.diseaseList = new LinkedList<>();
    }

    public long getPatientId() {
        return patientId;
    }

    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Feature> getFeatureList() {
        return featureList;
    }

    public void setFeatureList(List<Feature> featureList) {
        this.featureList = featureList;
    }

    public List<Disease> getDiseaseList() {
        return diseaseList;
    }

    public void setDiseaseList(List<Disease> diseaseList) {
        this.diseaseList = diseaseList;
    }

    public void addFeature(Feature feature) {
        feature.setPatient(this);
        featureList.add(feature);
    }

    public void addDisease(Disease disease) {
        disease.setPatient(this);
        diseaseList.add(disease);
    }
}
