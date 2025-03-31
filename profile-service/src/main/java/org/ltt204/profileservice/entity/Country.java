package org.ltt204.profileservice.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

@Data
@Builder
@Node("country")
public class Country {
    @Id
    @GeneratedValue(generatorClass = UUIDStringGenerator.class)
    String countryId;
    String countryName;
    String countryCode;
    String zipCode;
    String postalCode;
}
