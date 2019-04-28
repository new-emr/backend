package com.emr.dmr_demo.Controllers;

import com.emr.dmr_demo.Entities.Disease;
import com.emr.dmr_demo.Entities.Feature;
import com.emr.dmr_demo.Entities.Patient;
import com.emr.dmr_demo.Repositories.PatientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
@CrossOrigin
public class Controller {
    private PatientRepository patientRepository;

    public Controller(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @GetMapping("/getAllPatient")
    public ResponseEntity<Iterable<Patient>> getAllPatient() {
        return new ResponseEntity<>(patientRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<Patient> getPatientById(@PathVariable long patientId) {
        Patient patient = patientRepository.findById(patientId).orElse(null);
        return new ResponseEntity<>(patient, patient == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
    }

    @PostMapping("/addPatient")
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient) {
        Patient storedPatient = patientRepository.save(new Patient(patient.getName()));
        for(Feature feature: patient.getFeatureList()) {

            storedPatient.addFeature(feature);
        }
        for(Disease disease: patient.getDiseaseList()) {
            storedPatient.addDisease(disease);
        }
        storedPatient = patientRepository.save(storedPatient);
        return new ResponseEntity<>(storedPatient, HttpStatus.OK);
    }

    @PostMapping("/addDisease")
    public ResponseEntity<Patient> addDisease(@RequestBody Disease disease) {
        Patient patient = patientRepository.findById(disease.getPatient().getPatientId()).orElse(null);
        if(patient != null) {
            patient.addDisease(disease);
            return new ResponseEntity<>(patient, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
