package com.emr.dmr_demo.Repositories;

import com.emr.dmr_demo.Entities.Feature;
import org.springframework.data.repository.CrudRepository;


public interface FeatureRepository extends CrudRepository<Feature, Long> {
}
