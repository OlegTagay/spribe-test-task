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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;

public class RandomPlayer {
    private static final Logger log = LoggerFactory.getLogger(RandomPlayer.class);

    public static PlayerCreateResponse createPlayer() {
        log.info("Start creating RandomPlayer");
        PlayerCreateRequest request = new PlayerCreateRequest(
                "18", Gender.MALE, StringUtils.generateLogin(), StringUtils.generatePassword(), Role.USER, StringUtils.generateScreenName()
        );

        PlayerCreateResponse response =
                given()
                        .baseUri(ConfigManager.getBaseUri())
                        .when()
                        .log()
                        .all()
                        .pathParam("editor", Editor.SUPERVISOR)
                        .queryParams(RequestUtils.toMap(request))
                        .get("/player/create/{editor}")
                        .then()
                        .statusCode(HttpStatus.OK.code())
                        .extract()
                        .as(PlayerCreateResponse.class);

//        PlayerCreateResponse response = new PlayerCreateResponse(
//                18, "", new Random().nextLong(1000000, 9999999), "", "", "", ""
//        );

        log.info("Finish creating RandomPlayer.\nResponse: " + response);

        return response;
    }
}
