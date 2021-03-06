package com.emr.dmr_demo.Repositories;

import com.emr.dmr_demo.Entities.Record;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecordRepository extends CrudRepository<Record, Long>, RecordRepositoryCustom {
    List<Record> findAll();
}
