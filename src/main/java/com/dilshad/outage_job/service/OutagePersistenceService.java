package com.dilshad.outage_job.service;

public interface OutagePersistenceService {
    void saveOutage(String outageId, String status);
}