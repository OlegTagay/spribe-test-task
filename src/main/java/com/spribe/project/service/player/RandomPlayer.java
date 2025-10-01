package com.spribe.project.service.player;

import com.spribe.project.config.ConfigManager;
import com.spribe.project.enums.Editor;
import com.spribe.project.enums.Gender;
import com.spribe.project.enums.Role;
import com.spribe.project.models.request.create.PlayerCreateRequest;
import com.spribe.project.models.response.create.PlayerCreateResponse;
import com.spribe.project.utils.RequestUtils;

import static io.restassured.RestAssured.given;

public class RandomPlayer extends PlayerFactory {

    @Override
    public PlayerCreateResponse createPlayer() {
        PlayerCreateRequest request = new PlayerCreateRequest(
                "18", Gender.MALE, "", "", Role.USER, ""
        );

        PlayerCreateResponse response =
                given()
                        .baseUri(ConfigManager.getBaseUri())
                        .when()
                        .pathParam("editor", Editor.ADMIN)
                        .queryParams(RequestUtils.toMap(request))
                        .get("/player/create/{editor}")
                        .then()
                        .statusCode(200)
                        .extract()
                        .as(PlayerCreateResponse.class);

        System.out.println(response);

        return response;
    }
}
