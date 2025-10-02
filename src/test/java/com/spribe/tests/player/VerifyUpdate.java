package com.spribe.tests.player;

import com.spribe.project.config.ConfigManager;
import com.spribe.project.enums.Editor;
import com.spribe.project.enums.Gender;
import com.spribe.project.models.common.HttpStatus;
import com.spribe.project.models.response.get.PlayerItemResponse;
import com.spribe.project.models.response.update.PlayerUpdateResponse;
import com.spribe.project.service.player.PlayerQueryService;
import com.spribe.project.utils.RequestUtils;
import com.spribe.tests.base.BaseTest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.testng.Tag;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Optional;

import static io.restassured.RestAssured.given;

public class VerifyUpdate extends BaseTest {

    @Severity(SeverityLevel.CRITICAL)
    @Tag("Smoke")
    @Tag("Update")
    @Test(description = "Update user screen name should return 200")
    public void createUserScreenName_shouldReturn200() {
        PlayerQueryService playerQueryService = new PlayerQueryService();
        Optional<PlayerItemResponse> playerItemResponse = playerQueryService.getById(randomPlayerResponse.get().getId());

        String expectedField = "atf-updated";

        playerItemResponse.get().setScreenName(expectedField);

        PlayerUpdateResponse response =
                given()
                        .baseUri(ConfigManager.getBaseUri())
                        .when()
                        .header("Content-Type", ContentType.JSON)
                        .pathParam("editor", Editor.SUPERVISOR)
                        .pathParam("id", playerItemResponse.get().getId())
                        .body(playerItemResponse.get())
                        .log().all()
                        .patch("/player/update/{editor}/{id}")
                        .then()
                        .log().all()
                        .statusCode(HttpStatus.OK.code())
                        .extract()
                        .as(PlayerUpdateResponse.class);


        Assert.assertEquals(playerItemResponse.get().getScreenName(), expectedField);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Tag("Smoke")
    @Tag("Update")
    @Test(description = "Update user screen name should return 200")
    public void createUserGender_shouldReturn200() {
        PlayerQueryService playerQueryService = new PlayerQueryService();
        Optional<PlayerItemResponse> playerItemResponse = playerQueryService.getById(randomPlayerResponse.get().getId());

        playerItemResponse.get().setGender(Gender.FEMALE.toString());

        PlayerUpdateResponse response =
                given()
                        .baseUri(ConfigManager.getBaseUri())
                        .when()
                        .header("Content-Type", ContentType.JSON)
                        .pathParam("editor", Editor.SUPERVISOR)
                        .pathParam("id", playerItemResponse.get().getId())
                        .body(playerItemResponse.get())
                        .log().all()
                        .patch("/player/update/{editor}/{id}")
                        .then()
                        .log().all()
                        .statusCode(HttpStatus.OK.code())
                        .extract()
                        .as(PlayerUpdateResponse.class);


        Assert.assertEquals(playerItemResponse.get().getGender(), Gender.FEMALE.toString());
    }

    @Severity(SeverityLevel.CRITICAL)
    @Tag("Smoke")
    @Tag("Update")
    @Test(description = "Update user screen name should return 403")
    public void createUserGenderWithInvalidEditor_shouldReturn403() {
        PlayerQueryService playerQueryService = new PlayerQueryService();
        Optional<PlayerItemResponse> playerItemResponse = playerQueryService.getById(randomPlayerResponse.get().getId());

        playerItemResponse.get().setGender(Gender.FEMALE.toString());

        given()
                .baseUri(ConfigManager.getBaseUri())
                .when()
                .header("Content-Type", ContentType.JSON)
                .pathParam("editor", Editor.INVALID)
                .pathParam("id", playerItemResponse.get().getId())
                .body(playerItemResponse.get())
                .log().all()
                .patch("/player/update/{editor}/{id}")
                .then()
                .log().all()
                .statusCode(HttpStatus.FORBIDDEN.code());
    }
}
