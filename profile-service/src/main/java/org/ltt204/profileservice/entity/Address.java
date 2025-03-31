package org.ltt204.profileservice.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

@Data
@Builder
@Node("address")
public class Address {
    @Id
    @GeneratedValue(generatorClass = UUIDStringGenerator.class)
    String id;
    String city;
    String street;
    String ward;
    String district;
    String province;
    @Relationship(type = "BELONG_TO", direction = Relationship.Direction.OUTGOING)
    Country country;
}
