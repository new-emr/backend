package com.emr.dmr_demo.Controllers;

import com.emr.dmr_demo.Entities.Disease;
import com.emr.dmr_demo.Entities.Feature;
import com.emr.dmr_demo.Entities.Patient;
import com.emr.dmr_demo.Entities.Record;
import com.emr.dmr_demo.Repositories.PatientRepository;
import com.emr.dmr_demo.Repositories.RecordRepository;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/record")
public class RecordController {
    private RecordRepository recordRepository;
    private PatientRepository patientRepository;

    public RecordController(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Record> getById(@PathVariable long id) {
        Record record = recordRepository.findById(id).orElse(null);
        return new ResponseEntity<>(record, record == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
    }

    @GetMapping("getAll")
    public ResponseEntity<List<Record>> getAll() {
        List<Record> recordList = recordRepository.findAll();
        return new ResponseEntity<>(recordList, recordList == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Record> add(@RequestBody Record record) {
        Record storedRecord = recordRepository.save(new Record(record.getName()));
        Patient patient = patientRepository.findById(record.getPatient().getId()).orElse(null);
        if(patient != null) {
            patient.addRecord(storedRecord);
            patientRepository.save(patient);
        }
        for(Feature feature: record.getFeatureList()) {
            storedRecord.addFeature(feature);
        }
        for(Disease disease: record.getDiseaseList()) {
            storedRecord.addDisease(disease);
        }
        storedRecord = recordRepository.save(storedRecord);
        return new ResponseEntity<>(storedRecord, HttpStatus.OK);
    }
}
