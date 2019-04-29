package com.emr.dmr_demo.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.PageRequest;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @OneToMany(mappedBy = "record", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("record")
    private List<Feature> featureList;
    @OneToMany(mappedBy = "record", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("record")
    private List<Disease> diseaseList;
    @ManyToOne
    @JoinColumn(name = "patientId")
    @JsonIgnoreProperties("recordList")
    private Patient patient;

    public Record(){}

    public Record(String name) {
        this.name = name;
        this.featureList = new LinkedList<>();
        this.diseaseList = new LinkedList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void addFeature(Feature feature) {
        featureList.add(feature);
        feature.setRecord(this);
    }
    public void addDisease(Disease disease) {
        diseaseList.add(disease);
        disease.setRecord(this);
    }
}
