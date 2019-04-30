package com.emr.dmr_demo.Repositories;

import com.emr.dmr_demo.Entities.Record;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class RecordRepositoryCustomImpl implements RecordRepositoryCustom{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Record> findByQuery(String query) {
        List<Record> recordList = entityManager.createQuery("select r from Record r inner join r.featureList f inner join r.diseaseList d where " + query, Record.class)
                .getResultList();
        return recordList;
    }
}
