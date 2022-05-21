package com.gameloft.profilematcher.data.repository;

import com.gameloft.profilematcher.data.entities.CampaignMatcher;
import com.gameloft.profilematcher.data.entities.PlayerProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.*;

public class UserRepositoryImpl implements CustomUserRepository<PlayerProfile, String> {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public UserRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public PlayerProfile returnProfileIfMatchingCampaign(String playerId, CampaignMatcher matcher) {
        var level = matcher.level();
        var has = matcher.has();
        var does_not_have = matcher.does_not_have();

        var query = new Query();

        //player id
        query.addCriteria(Criteria.where("player_id").is(playerId));

        //player level
        query.addCriteria(Criteria
                .where("level")
                .gte(level.min())
                .lte(level.max()));

        var criterias = new HashMap<String, List<Criteria>>();

        //has and does_not_have fields
        parseMatcherFieldPossession(has, false, criterias);
        parseMatcherFieldPossession(does_not_have, true, criterias);

        criterias.values().forEach(listCrit -> listCrit.forEach(query::addCriteria));

        return mongoTemplate.findOne(query, PlayerProfile.class);
    }

    private void parseMatcherFieldPossession(Map<String, List<String>> possession, boolean not, Map<String, List<Criteria>> criterias) {
        for (var entry : possession.entrySet()) {
            var fieldName = entry.getKey();
            var values = entry.getValue();

            if (!criterias.containsKey(fieldName)) {
                criterias.put(fieldName, criteriaFromField(fieldName, values, null, not));
            } else {
                List<Criteria> listCriteria = criterias.get(fieldName);
                criteriaFromField(fieldName, values, listCriteria, not);
            }
        }
    }

    private List<Criteria> criteriaFromField(String fieldName, List<String> values, List<Criteria> criterias, boolean not) {
        List<Criteria> criteriaList;

        if ("items".equalsIgnoreCase(fieldName)) {
            criteriaList = criterias == null ? new ArrayList<>() : criterias;
            values.forEach(value -> criteriaList.add(Criteria.where(String.format("inventory.%s", value)).exists(!not)));
        } else {
            criteriaList = criterias == null ? new ArrayList<>(List.of(Criteria.where(fieldName))) : criterias;
            Criteria criteria = criteriaList.get(0);
            if (not) {
                criteria.nin(values);
            } else {
                criteria.in(values);
            }
        }
        return criteriaList;
    }
}
