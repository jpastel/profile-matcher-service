package com.gameloft.profilematcher.data.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Document
public record PlayerProfile(@Id UUID player_id,
                            String credential,
                            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss'Z'") Date created,
                            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss'Z'") Date modified,
                            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss'Z'") Date last_session,
                            int total_spent,
                            int total_refund,
                            int total_transactions,
                            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss'Z'") Date last_purchase,
                            List<Campaign> active_campaigns,
                            List<Device> devices,
                            int level,
                            int xp,
                            int total_playtime,
                            String country,
                            String language,
                            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss'Z'") Date birthdate,
                            String gender,
                            Map<String, Integer> inventory,
                            Clan clan,
                            String _customfield) {
}
