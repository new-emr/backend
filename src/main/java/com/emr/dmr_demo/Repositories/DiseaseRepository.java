package com.emr.dmr_demo.Repositories;

import com.emr.dmr_demo.Entities.Disease;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface DiseaseRepository extends CrudRepository<Disease, Long> {
}
