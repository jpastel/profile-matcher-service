package com.gameloft.profilematcher.client;

import com.gameloft.profilematcher.data.entities.Campaign;
import com.gameloft.profilematcher.utils.JSONUtil;
import org.springframework.stereotype.Component;

@Component
public class CampaignClient {

    public Campaign getCurrentCampaign() {
        String JSON_CAMPAIGN = """
                {
                  "game": "mygame",
                  "name": "mycampaign",
                  "priority": 10.5,
                  "matchers": {
                    "level": {
                      "min": 1,
                      "max": 3
                    },
                    "has": {
                      "country": [
                        "US",
                        "RO",
                        "CA"
                      ],
                      "items": [
                        "item_1"
                      ]
                    },
                    "does_not_have": {
                      "items": [
                        "item_4"
                      ]
                    }
                  },
                  "start_date": "2022-01-25 00:00:00Z",
                  "end_date": "2022-02-25 00:00:00Z",
                  "enabled": true,
                  "last_updated": "2021-07-13 11:46:58Z"
                }
                """;

        return JSONUtil.getPOJOFromJsonString(JSON_CAMPAIGN, Campaign.class);
    }
}
