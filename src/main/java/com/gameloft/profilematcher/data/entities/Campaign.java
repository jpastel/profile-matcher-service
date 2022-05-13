package com.gameloft.profilematcher.data.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public record Campaign(String game,
                       String name,
                       float priority,
                       CampaignMatcher matchers,
                       @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss'Z'") Date start_date,
                       @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss'Z'") Date end_date,
                       boolean enabled,
                       @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss'Z'") Date last_updated) {
}
