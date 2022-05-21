package com.gameloft.profilematcher.data.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Document(collection = "profile")
public record PlayerProfile(@Id String id,
                            String player_id,
                            String credential,
                            String created,
                            String modified,
                            String last_session,
                            int total_spent,
                            int total_refund,
                            int total_transactions,
                            String last_purchase,
                            List<Campaign> active_campaigns,
                            List<Device> devices,
                            int level,
                            int xp,
                            int total_playtime,
                            String country,
                            String language,
                            String birthdate,
                            String gender,
                            Map<String, Integer> inventory,
                            Clan clan,
                            String _customfield) {

    public PlayerProfile withCampaign(Campaign campaign) {
        if(!active_campaigns().contains(campaign)) {
            List<Campaign> campaigns = new ArrayList<>(active_campaigns());
            campaigns.add(campaign);

            return new PlayerProfile(id(),
                    player_id(),
                    credential(),
                    created(),
                    modified(),
                    last_session(),
                    total_spent(),
                    total_refund(),
                    total_transactions(),
                    last_purchase(),
                    campaigns,
                    devices(),
                    level(),
                    xp(),
                    total_playtime(),
                    country(),
                    language(),
                    birthdate(),
                    gender(),
                    inventory(),
                    clan(),
                    _customfield()
            );
        } else {
            return this;
        }
    }
}
