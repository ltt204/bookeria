package org.ltt204.profileservice.repository;

import org.ltt204.profileservice.entity.Country;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface CountryRepository extends Neo4jRepository<Country,String> {
}
