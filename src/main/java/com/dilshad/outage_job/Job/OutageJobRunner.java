package com.dilshad.outage_job.Job;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class OutageJobRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("==============================================");
        System.out.println("Starting Outage Scheduled Job Execution...");
        System.out.println("==============================================");

        String regionStr = System.getenv("AWS_REGION");
        Region region = (regionStr != null) ? Region.of(regionStr) : Region.US_EAST_1;

        try (DynamoDbClient dynamoDbClient = DynamoDbClient.builder().region(region).build()) {
            String outageId = UUID.randomUUID().toString();

            Map<String, AttributeValue> item = new HashMap<>();
            item.put("outageId", AttributeValue.builder().s(outageId).build());
            item.put("status", AttributeValue.builder().s("PROCESSED_BY_FARGATE").build());
            item.put("timestamp", AttributeValue.builder().s(String.valueOf(System.currentTimeMillis())).build());

            PutItemRequest request = PutItemRequest.builder()
                    .tableName("outage-records")
                    .item(item)
                    .build();

            dynamoDbClient.putItem(request);
            System.out.println("SUCCESS: Recorded outage entry [" + outageId + "] into DynamoDB table 'outage-records'.");
        } catch (Exception e) {
            System.err.println("ERROR: Failed to write item to DynamoDB: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("==============================================");
        System.out.println("Job Execution finished. Terminating container...");
        System.out.println("==============================================");
    }
}