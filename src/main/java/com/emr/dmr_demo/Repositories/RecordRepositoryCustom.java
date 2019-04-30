package com.emr.dmr_demo.Repositories;

import com.emr.dmr_demo.Entities.Record;

import java.util.List;

public interface RecordRepositoryCustom {
    List<Record> findByQuery(String query);
}
