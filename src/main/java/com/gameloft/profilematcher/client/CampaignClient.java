package com.gameloft.profilematcher.client;

import com.gameloft.profilematcher.data.entities.Campaign;
import com.gameloft.profilematcher.utils.JSONUtil;
import org.springframework.stereotype.Component;

@Component
public class CampaignClient {

    public Campaign getCurrentCampaign() {
        return JSONUtil.getPOJOFromJsonFile("campaign.json", Campaign.class);
    }
}
