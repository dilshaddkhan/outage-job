package com.dilshad.outage_job.Job;

import com.dilshad.outage_job.service.OutagePersistenceService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class OutageJobRunner implements CommandLineRunner {

    private final OutagePersistenceService persistenceService;

    // Inject "documentDbPersistence" OR "dynamoDbPersistence"
    public OutageJobRunner(@Qualifier("dynamoDbPersistence") OutagePersistenceService persistenceService) {
        this.persistenceService = persistenceService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("==============================================");
        System.out.println("Starting Outage Scheduled Job Execution...");
        System.out.println("==============================================");

        String outageId = UUID.randomUUID().toString();
        persistenceService.saveOutage(outageId, "PROCESSED_BY_FARGATE");

        System.out.println("==============================================");
        System.out.println("Job Execution finished. Terminating container...");
        System.out.println("==============================================");
    }
}