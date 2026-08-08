package com.dilshad.outage_job.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

//@Document(collection = "outage_records")
@Data                       // Generates Getters, Setters, toString, equals, and hashCode
@NoArgsConstructor          // Required by Spring Data / MongoDB mapping to instantiate objects
@AllArgsConstructor         // Generates constructor for all fields
@Builder                    // (Optional) Enables Builder pattern for clean object creation
public class OutageDocument {

  //  @Id
    private String id;
    private String outageId;
    private String description;
    private String status;

    @Builder.Default
    private String createdAt = Instant.now().toString();

}