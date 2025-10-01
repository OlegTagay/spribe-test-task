package com.spribe.tests.player;

import com.spribe.project.config.ConfigManager;
import com.spribe.project.models.common.HttpStatus;
import com.spribe.project.models.request.get.PlayerGetByPlayerIdRequest;
import com.spribe.project.models.response.get.GetAllPlayersResponse;
import com.spribe.project.models.response.get.PlayerGetByPlayerIdResponse;
import com.spribe.project.service.player.PlayerQueryService;
import com.spribe.project.utils.RequestUtils;
import com.spribe.tests.base.BaseTest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.testng.Tag;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class VerifyGet extends BaseTest {

    @Severity(SeverityLevel.CRITICAL)
    @Tag("Smoke")
    @Tag("Get")
    @Test(description = "Get user should return 200")
    public void getUser_shouldReturn200() {
        PlayerGetByPlayerIdRequest request = new PlayerGetByPlayerIdRequest(randomPlayerResponse.get().getId());

        PlayerGetByPlayerIdResponse response = given()
                .baseUri(ConfigManager.getBaseUri())
                .when()
                .header("Content-Type", ContentType.JSON)
                .body(RequestUtils.toMap(request))
                .post("/player/get")
                .then()
                .statusCode(HttpStatus.OK.code())
                .extract()
                .as(PlayerGetByPlayerIdResponse.class);

        Assert.assertEquals(request.getPlayerId(), response.getId());
    }

    @Severity(SeverityLevel.CRITICAL)
    @Tag("Smoke")
    @Tag("Get")
    @Test(description = "Get admin should return 200")
    public void getAdmin_shouldReturn200() {
        PlayerGetByPlayerIdRequest request = new PlayerGetByPlayerIdRequest(adminPlayerResponse.get().getId());

        PlayerGetByPlayerIdResponse response = given()
                .baseUri(ConfigManager.getBaseUri())
                .when()
                .header("Content-Type", ContentType.JSON)
                .body(RequestUtils.toMap(request))
                .post("/player/get")
                .then()
                .statusCode(HttpStatus.OK.code())
                .extract()
                .as(PlayerGetByPlayerIdResponse.class);

        Assert.assertEquals(request.getPlayerId(), response.getId());
    }

    @Severity(SeverityLevel.CRITICAL)
    @Tag("Smoke")
    @Tag("Get")
    @Test(description = "Get user should return 200")
    public void getAllUsers_shouldReturn200() {
        PlayerGetByPlayerIdRequest request = new PlayerGetByPlayerIdRequest(randomPlayerResponse.get().getId());

        GetAllPlayersResponse response =
                RestAssured.given()
                        .baseUri(ConfigManager.getBaseUri())
                        .when()
                        .get("/player/get/all")
                        .then()
                        .statusCode(HttpStatus.OK.code())
                        .extract()
                        .as(GetAllPlayersResponse.class);

        Assert.assertTrue(new PlayerQueryService().exists(request.getPlayerId()));
    }
}
