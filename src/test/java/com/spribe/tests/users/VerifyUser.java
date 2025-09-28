package com.spribe.tests.users;

import com.spribe.project.config.ConfigManager;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.testng.Tag;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

@Epic("User API")
@Feature("User Management")
public class VerifyUser {

    @Severity(SeverityLevel.CRITICAL)
    @Tag("Smoke")
    @Test(description = "Create a user successfully")
    public void createUser_shouldReturn201() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        given()
                .log()
                .all()
                .when()
                .get(ConfigManager.getBaseUri() + "/player/get/all")
                .then()
                .statusCode(200);
    }
}
