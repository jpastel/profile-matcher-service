package com.gameloft.profilematcher.service;

import com.gameloft.profilematcher.client.CampaignClient;
import com.gameloft.profilematcher.data.entities.PlayerProfile;
import com.gameloft.profilematcher.data.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MatcherService {

    private final UserRepository userRepository;
    private final CampaignClient campaignClient;

    public MatcherService(UserRepository userRepository, CampaignClient campaignClient) {
        this.userRepository = userRepository;
        this.campaignClient = campaignClient;
    }

    public PlayerProfile returnPlayerWithCampaignIfMatch(UUID playerID) {
        var currentCampaign = campaignClient.getCurrentCampaign();
        var playerProfile = userRepository.returnProfileIfMatchingCampaign(playerID, currentCampaign.matchers());

        if (playerProfile != null) {
            return null;
        }

        return null;
    }
}
