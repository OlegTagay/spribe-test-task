package com.spribe.tests.player;

import com.spribe.project.config.ConfigManager;
import com.spribe.project.enums.Editor;
import com.spribe.project.enums.Gender;
import com.spribe.project.enums.Role;
import com.spribe.project.models.common.HttpStatus;
import com.spribe.project.models.request.create.PlayerCreateRequest;
import com.spribe.project.models.request.get.PlayerGetByPlayerIdRequest;
import com.spribe.project.models.response.create.PlayerCreateResponse;
import com.spribe.project.models.response.get.GetAllPlayersResponse;
import com.spribe.project.utils.RequestUtils;
import com.spribe.project.utils.StringUtils;
import com.spribe.tests.base.BaseTest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.testng.Tag;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class VerifySchema extends BaseTest {

    @Severity(SeverityLevel.CRITICAL)
    @Tag("Smoke")
    @Tag("Schema")
    @Test(description = "Create user should have correct schema")
    public void createUser_shouldCheckSchema() {
        PlayerCreateRequest request = new PlayerCreateRequest(
                "18", Gender.MALE, StringUtils.generateLogin(), StringUtils.generatePassword(), Role.USER, StringUtils.generateScreenName()
        );

        given()
                .baseUri(ConfigManager.getBaseUri())
                .when()
                .pathParam("editor", Editor.SUPERVISOR)
                .queryParams(RequestUtils.toMap(request))
                .get("/player/create/{editor}")
                .then()
                .statusCode(HttpStatus.OK.code())
                .body(matchesJsonSchemaInClasspath("schema/response/create/PlayerCreateResponse.json"));
    }

    @Severity(SeverityLevel.CRITICAL)
    @Tag("Smoke")
    @Tag("Schema")
    @Test(description = "Get user should have correct schema")
    public void getUser_shouldCheckSchema() {
        PlayerGetByPlayerIdRequest request = new PlayerGetByPlayerIdRequest(randomPlayerResponse.get().getId());

        given()
                .baseUri(ConfigManager.getBaseUri())
                .when()
                .header("Content-Type", ContentType.JSON)
                .body(RequestUtils.toMap(request))
                .post("/player/get")
                .then()
                .statusCode(HttpStatus.OK.code())
                .body(matchesJsonSchemaInClasspath("schema/response/get/PlayerGetByPlayerIdResponse.json"));
    }
}
