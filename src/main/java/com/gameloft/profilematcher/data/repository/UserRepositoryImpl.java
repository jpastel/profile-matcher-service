package com.gameloft.profilematcher.data.repository;

import com.gameloft.profilematcher.data.entities.CampaignMatcher;
import com.gameloft.profilematcher.data.entities.PlayerProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.HashMap;
import java.util.UUID;

public class UserRepositoryImpl implements CustomUserRepository<PlayerProfile, UUID> {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public UserRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public PlayerProfile returnProfileIfMatchingCampaign(UUID uuid, CampaignMatcher matcher) {
        var level = matcher.level();
        var has = matcher.has();
        var does_not_have = matcher.does_not_have();

        var query = new Query();

        //player id
        query.addCriteria(Criteria.where("player_id").is(uuid));

        //player level
        query.addCriteria(Criteria
                .where("level")
                .gte(level.min())
                .lte(level.max()));

        var criteriaMap = new HashMap<String, Criteria>();

        //has fields
        for (var entry : has.entrySet()) {
            var fieldName = entry.getKey();
            var values = entry.getValue();

            if (!criteriaMap.containsKey(fieldName)) {
                criteriaMap.put(fieldName, Criteria.where(toPlayerProfileField(fieldName)).in(values));
            } else {
                criteriaMap.get(fieldName).in(values);
            }
        }

        //does_not_have fields
        for (var entry : does_not_have.entrySet()) {
            var fieldName = entry.getKey();
            var values = entry.getValue();

            if (!criteriaMap.containsKey(fieldName)) {
                criteriaMap.put(fieldName, Criteria.where(toPlayerProfileField(fieldName)).nin(values));
            } else {
                criteriaMap.get(fieldName).nin(values);
            }
        }

        criteriaMap.values().forEach(query::addCriteria);

        return mongoTemplate.findOne(query, PlayerProfile.class);
    }

    private String toPlayerProfileField(String name) {
        if ("items".equalsIgnoreCase(name)) {
            return "inventory";
        }
        return name;
    }
}
