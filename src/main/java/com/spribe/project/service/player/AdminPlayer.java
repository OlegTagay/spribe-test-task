package com.spribe.project.service.player;

import com.spribe.project.config.ConfigManager;
import com.spribe.project.enums.Editor;
import com.spribe.project.enums.Gender;
import com.spribe.project.enums.Role;
import com.spribe.project.models.common.HttpStatus;
import com.spribe.project.models.request.create.PlayerCreateRequest;
import com.spribe.project.models.response.create.PlayerCreateResponse;
import com.spribe.project.utils.RequestUtils;
import com.spribe.project.utils.StringUtils;

import static io.restassured.RestAssured.given;

public class AdminPlayer {

    public static PlayerCreateResponse createPlayer() {
        PlayerCreateRequest request = new PlayerCreateRequest(
                "18", Gender.MALE, StringUtils.generateLogin(), StringUtils.generatePassword(), Role.ADMIN, StringUtils.generateScreenName()
        );

        PlayerCreateResponse response =
                given()
                        .baseUri(ConfigManager.getBaseUri())
                        .log()
                        .all()
                        .when()
                        .pathParam("editor", Editor.SUPERVISOR)
                        .queryParams(RequestUtils.toMap(request))
                        .get("/player/create/{editor}")
                        .then()
                        .statusCode(HttpStatus.OK.code())
                        .extract()
                        .as(PlayerCreateResponse.class);

        System.out.println(response);

        return response;
    }
}
