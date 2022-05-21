package com.gameloft.profilematcher.controller;

import com.gameloft.profilematcher.data.entities.PlayerProfile;
import com.gameloft.profilematcher.service.MatcherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MatcherRestController {

    private final MatcherService matcherService;

    public MatcherRestController(MatcherService matcherService) {
        this.matcherService = matcherService;
    }

    @GetMapping("/get_client_config/{player_id}")
    public ResponseEntity<PlayerProfile> getMatchingProfile(@PathVariable("player_id") String playerProfileID) {
        PlayerProfile profile = matcherService.returnPlayerWithCampaignIfMatch(playerProfileID);

        if (profile != null) {
            return ResponseEntity.ok(profile);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
