package com.spribe.tests.users;

import com.spribe.project.config.ConfigManager;
import com.spribe.project.models.response.get.GetAllPlayersResponse;
import com.spribe.project.models.response.get.PlayerItemResponse;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.testng.Tag;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

@Epic("User API")
@Feature("User Management")
public class VerifyUser {

    @Severity(SeverityLevel.CRITICAL)
    @Tag("Smoke")
    @Test(description = "Create a user successfully")
    public void total_equals_1165() {
        GetAllPlayersResponse response =
                given()
                        .baseUri(ConfigManager.getBaseUri())
                        .when()
                        .get("/player/get/all")
                        .then()
                        .statusCode(200)
                        .extract()
                        .as(GetAllPlayersResponse.class);

        List<PlayerItemResponse> total = response.getPlayers().stream().collect(Collectors.toList());

        Assert.assertEquals(total.size(), 1165);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Tag("Smoke")
    @Test(description = "Create a user successfully")
    public void createUser_shouldReturn201() {
        GetAllPlayersResponse response =
                given()
                        .baseUri(ConfigManager.getBaseUri())
                        .when()
                        .get("/player/get/all")
                        .then()
                        .statusCode(200)
                        .extract()
                        .as(GetAllPlayersResponse.class);

        List<PlayerItemResponse> total = response.getPlayers().stream().collect(Collectors.toList());
        System.out.println("Total: " + total.size());

        List<PlayerItemResponse> filtered = response.getPlayers().stream().filter(player -> player.getAge() <= 16).collect(Collectors.toList());

        System.out.println(filtered);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Tag("Smoke")
    @Test(description = "Create a user successfully")
    public void createUser_shouldCheckSchema() {

        given()
                .baseUri(ConfigManager.getBaseUri())
                .when()
                .header("Content-Type", ContentType.JSON)
                .body("{\"playerId\": 603162066}")
                .post("/player/get")
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schema/response/get/PlayerGetByPlayerIdResponse.json"));
    }
}
