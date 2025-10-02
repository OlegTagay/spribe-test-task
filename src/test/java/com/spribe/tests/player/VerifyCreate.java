package com.spribe.tests.player;

import com.spribe.project.assertions.PlayerAssertions;
import com.spribe.project.config.ConfigManager;
import com.spribe.project.enums.Editor;
import com.spribe.project.enums.Gender;
import com.spribe.project.enums.Role;
import com.spribe.project.models.common.HttpStatus;
import com.spribe.project.models.request.create.PlayerCreateRequest;
import com.spribe.project.models.response.create.PlayerCreateResponse;
import com.spribe.project.utils.RequestUtils;
import com.spribe.project.utils.StringUtils;
import io.qameta.allure.Issue;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.testng.Tag;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class VerifyCreate {
    @Severity(SeverityLevel.CRITICAL)
    @Tag("Smoke")
    @Tag("Create")
    @Test(description = "Create user should return 200")
    @Issue("Response contains null values, meanwhile user expects to have them set")
    @Ignore
    public void createUser_shouldReturn200() {
        PlayerCreateRequest request = new PlayerCreateRequest(
                "18", Gender.MALE, StringUtils.generateLogin(), StringUtils.generatePassword(), Role.USER, StringUtils.generateScreenName()
        );

        PlayerCreateResponse response =
                given()
                        .baseUri(ConfigManager.getBaseUri())
                        .when()
                        .pathParam("editor", Editor.SUPERVISOR)
                        .queryParams(RequestUtils.toMap(request))
                        .get("/player/create/{editor}")
                        .then()
                        .statusCode(HttpStatus.OK.code())
                        .extract()
                        .as(PlayerCreateResponse.class);


        PlayerAssertions.assertMatches(request, response);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Tag("Smoke")
    @Tag("Create")
    @Test(description = "Create admin should return 200")
    @Issue("Response contains null values, meanwhile user expects to have them set")
    @Ignore
    public void createAdmin_shouldReturn200() {
        PlayerCreateRequest request = new PlayerCreateRequest(
                "18", Gender.MALE, StringUtils.generateLogin(), StringUtils.generatePassword(), Role.ADMIN, StringUtils.generateScreenName()
        );

        PlayerCreateResponse response =
                given()
                        .baseUri(ConfigManager.getBaseUri())
                        .when()
                        .pathParam("editor", Editor.SUPERVISOR)
                        .queryParams(RequestUtils.toMap(request))
                        .get("/player/create/{editor}")
                        .then()
                        .statusCode(HttpStatus.OK.code())
                        .log()
                        .all()
                        .extract()
                        .as(PlayerCreateResponse.class);

        PlayerAssertions.assertMatches(request, response);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Tag("Smoke")
    @Tag("Create")
    @Test(description = "Create user under 16 should return 400")
    public void createUserUnder16_shouldReturn4xx() {
        PlayerCreateRequest request = new PlayerCreateRequest(
                "16", Gender.MALE, StringUtils.generateLogin(), StringUtils.generatePassword(), Role.USER, StringUtils.generateScreenName()
        );

        given()
                .baseUri(ConfigManager.getBaseUri())
                .when()
                .pathParam("editor", Editor.SUPERVISOR)
                .queryParams(RequestUtils.toMap(request))
                .get("/player/create/{editor}")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.code());

    }

    @Severity(SeverityLevel.CRITICAL)
    @Tag("Smoke")
    @Tag("Create")
    @Test(description = "Create user under 15 should return 400")
    public void createUserUnder15_shouldReturn4xx() {
        PlayerCreateRequest request = new PlayerCreateRequest(
                "15", Gender.MALE, StringUtils.generateLogin(), StringUtils.generatePassword(), Role.USER, StringUtils.generateScreenName()
        );

        given()
                .baseUri(ConfigManager.getBaseUri())
                .when()
                .pathParam("editor", Editor.SUPERVISOR)
                .queryParams(RequestUtils.toMap(request))
                .get("/player/create/{editor}")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.code());
    }

    @Severity(SeverityLevel.CRITICAL)
    @Tag("Smoke")
    @Tag("Create")
    @Test(description = "Create user over 60 should return 400")
    @Issue("Admin is not allowed to create users 60 yo")
    @Ignore
    public void createUserOver60_shouldReturn4xx() {
        PlayerCreateRequest request = new PlayerCreateRequest(
                "60", Gender.MALE, StringUtils.generateLogin(), StringUtils.generatePassword(), Role.USER, StringUtils.generateScreenName()
        );

        given()
                .baseUri(ConfigManager.getBaseUri())
                .when()
                .pathParam("editor", Editor.SUPERVISOR)
                .queryParams(RequestUtils.toMap(request))
                .get("/player/create/{editor}")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.code());
    }

    @Severity(SeverityLevel.CRITICAL)
    @Tag("Smoke")
    @Tag("Create")
    @Test(description = "Create user over 61 should return 400")
    public void createUserOver61_shouldReturn4xx() {
        PlayerCreateRequest request = new PlayerCreateRequest(
                "61", Gender.MALE, StringUtils.generateLogin(), StringUtils.generatePassword(), Role.USER, StringUtils.generateScreenName()
        );

        given()
                .baseUri(ConfigManager.getBaseUri())
                .when()
                .pathParam("editor", Editor.SUPERVISOR)
                .queryParams(RequestUtils.toMap(request))
                .get("/player/create/{editor}")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.code());
    }
}
