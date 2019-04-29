package com.emr.dmr_demo.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
public class Disease {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long diseaseId;
    private String name;
    private boolean isSick;
    @ManyToOne
    @JoinColumn(name = "recordId")
    @JsonIgnoreProperties("diseaseList")
    private Record record;

    public Disease() {
    }

    public Disease(String name, boolean isSick) {
        this.name = name;
        this.isSick = isSick;
    }

    public long getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(long diseaseId) {
        this.diseaseId = diseaseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSick() {
        return isSick;
    }

    public void setSick(boolean sick) {
        isSick = sick;
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }
}
