package com.emr.dmr_demo.Controllers;

import com.emr.dmr_demo.Entities.Record;
import com.emr.dmr_demo.Repositories.RecordRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/record")
public class RecordController {
    private RecordRepository recordRepository;

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
        record.getFeatureList().forEach(feature ->
                feature.setRecord(record));
        record.getDiseaseList().forEach(disease ->
                disease.setRecord(record));
        return new ResponseEntity<>(recordRepository.save(record), HttpStatus.OK);
    }
}
