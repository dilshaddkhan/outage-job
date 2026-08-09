package com.dilshad.outage_job.service.impl;

import com.dilshad.outage_job.entity.OutageDocument;
import com.dilshad.outage_job.repository.OutageDocumentRepository;
import com.dilshad.outage_job.service.OutagePersistenceService;
import org.springframework.stereotype.Service;

//@Service("documentDbPersistence")
public class DocumentDbPersistenceServiceImpl implements OutagePersistenceService {

    //private final OutageDocumentRepository repository;

 /*   public DocumentDbPersistenceServiceImpl(OutageDocumentRepository repository) {
        this.repository = repository;
    }*/

    @Override
    public void saveOutage(String outageId, String status) {
        try {
            OutageDocument document = OutageDocument.builder()
                    .outageId(outageId)
                    .description("Outage due to Network failure")
                    .status(status)
                    .build();
           // repository.save(document);
            System.out.println("SUCCESS: Recorded outage entry [" + outageId + "] into DocumentDB.");
        } catch (Exception e) {
            System.err.println("ERROR: Failed to write item to DocumentDB: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}