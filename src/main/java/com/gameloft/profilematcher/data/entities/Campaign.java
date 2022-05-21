package com.gameloft.profilematcher.data.entities;

public record Campaign(String game,
                       String name,
                       float priority,
                       CampaignMatcher matchers,
                       String start_date,
                       String end_date,
                       boolean enabled,
                       String last_updated) {
}
