package com.dilshad.outage_job.service.impl;

import com.dilshad.outage_job.service.OutagePersistenceService;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;

import java.util.HashMap;
import java.util.Map;

@Service("dynamoDbPersistence")
public class DynamoDbPersistenceServiceImpl implements OutagePersistenceService {

    @Override
    public void saveOutage(String outageId, String status) {
        String regionStr = System.getenv("AWS_REGION");
        Region region = (regionStr != null) ? Region.of(regionStr) : Region.US_EAST_1;

        try (DynamoDbClient dynamoDbClient = DynamoDbClient.builder().region(region).build()) {
            Map<String, AttributeValue> item = new HashMap<>();
            item.put("outageId", AttributeValue.builder().s(outageId).build());
            item.put("status", AttributeValue.builder().s(status).build());
            item.put("timestamp", AttributeValue.builder().s(String.valueOf(System.currentTimeMillis())).build());

            PutItemRequest request = PutItemRequest.builder()
                    .tableName("outage-records")
                    .item(item)
                    .build();

            dynamoDbClient.putItem(request);
            System.out.println("SUCCESS: Recorded outage entry [" + outageId + "] into DynamoDB.");
        } catch (Exception e) {
            System.err.println("ERROR: Failed to write item to DynamoDB: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}