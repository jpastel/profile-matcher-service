package com.gameloft.profilematcher.data.repository;

import com.gameloft.profilematcher.data.entities.CampaignMatcher;

public interface CustomUserRepository<T, ID> {
    T returnProfileIfMatchingCampaign(ID id, CampaignMatcher matcher);
}
