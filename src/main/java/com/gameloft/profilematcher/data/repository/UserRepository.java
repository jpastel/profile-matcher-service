package com.gameloft.profilematcher.data.repository;

import com.gameloft.profilematcher.data.entities.PlayerProfile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<PlayerProfile, String>, CustomUserRepository<PlayerProfile, String> {

}
