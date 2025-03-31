package org.ltt204.profileservice.repository;

import org.ltt204.profileservice.entity.Address;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface AddressRepository extends Neo4jRepository<Address,String> {
}
