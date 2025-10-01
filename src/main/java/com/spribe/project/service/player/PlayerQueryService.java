package com.spribe.project.service.player;

import com.spribe.project.config.ConfigManager;
import com.spribe.project.models.response.get.GetAllPlayersResponse;
import com.spribe.project.models.response.get.PlayerItemResponse;
import io.restassured.RestAssured;

import java.util.List;
import java.util.Optional;

public class PlayerQueryService {
    public boolean exists(Long playerId) {
        return getAll().stream()
                .anyMatch(p -> p.getId().equals(playerId));
    }


    public List<PlayerItemResponse> getAll() {
        GetAllPlayersResponse response =
                RestAssured.given()
                        .baseUri(ConfigManager.getBaseUri())
                        .when()
                        .get("/player/get/all")
                        .then()
                        .statusCode(200)
                        .extract()
                        .as(GetAllPlayersResponse.class);

        return response.getPlayers();
    }

    public Optional<PlayerItemResponse> getById(Long playerId) {
        return getAll().stream()
                .filter(p -> p.getId().equals(playerId))
                .findFirst();
    }
}

