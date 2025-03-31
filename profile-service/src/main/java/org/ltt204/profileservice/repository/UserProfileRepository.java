package org.ltt204.profileservice.repository;

import org.ltt204.profileservice.entity.UserProfile;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends Neo4jRepository<UserProfile, String>, PagingAndSortingRepository<UserProfile, String> {
}
