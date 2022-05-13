package com.gameloft.profilematcher.data.entities;

import java.util.List;
import java.util.Map;

public record CampaignMatcher(Level level,
                              Map<String, List<String>> has,
                              Map<String, List<String>> does_not_have) {
}
