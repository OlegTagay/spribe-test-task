package com.spribe.tests.player;

import com.spribe.project.config.ConfigManager;
import com.spribe.project.enums.Editor;
import com.spribe.project.models.common.HttpStatus;
import com.spribe.project.models.response.get.PlayerItemResponse;
import com.spribe.project.models.response.update.PlayerUpdateResponse;
import com.spribe.project.service.player.PlayerQueryService;
import com.spribe.project.utils.RequestUtils;
import com.spribe.tests.base.BaseTest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.testng.Tag;
import org.testng.annotations.Test;

import java.util.Optional;

import static io.restassured.RestAssured.given;

public class VerifyUpdate extends BaseTest {

    @Severity(SeverityLevel.CRITICAL)
    @Tag("Smoke")
    @Tag("Update")
    @Test(description = "Update user should return 200")
    public void createUser_shouldReturn200() {
        PlayerQueryService playerQueryService = new PlayerQueryService();
        Optional<PlayerItemResponse> playerItemResponse = playerQueryService.getById(randomPlayerResponse.get().getId());

        System.out.println("-".repeat(10));
        System.out.println(playerQueryService.getById(randomPlayerResponse.get().getId()));
        System.out.println("-".repeat(10));

        playerItemResponse.get().setScreenName("atf-updated");
        playerItemResponse.get().setAge(55);
        playerItemResponse.get().setGender("female");
        playerItemResponse.get().setRole("admin");

        PlayerUpdateResponse response =
                given()
                        .baseUri(ConfigManager.getBaseUri())
                        .when()
                        .pathParam("editor", Editor.SUPERVISOR)
                        .pathParam("id", randomPlayerResponse.get().getId())
                        .body(playerItemResponse.get())
                        .patch("/player/create/{editor}/{id}")
                        .then()
                        .statusCode(HttpStatus.OK.code())
                        .extract()
                        .as(PlayerUpdateResponse.class);


        System.out.println("-".repeat(10));
        System.out.println(playerQueryService.getById(randomPlayerResponse.get().getId()));
        System.out.println("-".repeat(10));
//        PlayerAssertions.assertMatches(request, response);
    }
}
