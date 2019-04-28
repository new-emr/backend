package com.emr.dmr_demo.Repositories;

import com.emr.dmr_demo.Entities.Patient;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PatientRepository extends CrudRepository<Patient, Long> {
}
