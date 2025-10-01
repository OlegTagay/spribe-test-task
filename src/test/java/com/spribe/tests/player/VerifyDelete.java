package com.spribe.tests.player;

import com.spribe.project.config.ConfigManager;
import com.spribe.project.enums.Editor;
import com.spribe.project.models.request.get.PlayerGetByPlayerIdRequest;
import com.spribe.project.models.response.get.PlayerItemResponse;
import com.spribe.project.service.player.PlayerQueryService;
import com.spribe.project.utils.RequestUtils;
import com.spribe.tests.base.BaseTest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.testng.Tag;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class VerifyDelete extends BaseTest {
    @Severity(SeverityLevel.CRITICAL)
    @Tag("Smoke")
    @Tag("Delete")
    @Test(description = "Delete user should return 204")
    public void deleteUser_shouldReturn204() {
        PlayerGetByPlayerIdRequest request = new PlayerGetByPlayerIdRequest(randomPlayerResponse.get().getId());

        given()
                .baseUri(ConfigManager.getBaseUri())
                .when()
                .pathParam("editor", Editor.ADMIN)
                .header("Content-Type", ContentType.JSON)
                .body(RequestUtils.toMap(request))
                .delete("/player/delete/{editor}")
                .then()
                .statusCode(204);

        Assert.assertFalse(new PlayerQueryService().exists(request.getPlayerId()));
    }

    @Severity(SeverityLevel.CRITICAL)
    @Tag("Smoke")
    @Tag("Delete")
    @Test(description = "Delete user should return 204")
    public void deleteNonExistingUser_shouldReturn400() {
        PlayerQueryService service = new PlayerQueryService();

        long maxId = service.getAll().stream()
                .mapToLong(PlayerItemResponse::getId)
                .max()
                .orElseThrow(() -> new IllegalStateException("No players found"));

        long invalidId = maxId + 1000; //Thread-safe

        PlayerGetByPlayerIdRequest request = new PlayerGetByPlayerIdRequest(invalidId);

        Assert.assertFalse(new PlayerQueryService().exists(request.getPlayerId()));

        given()
                .baseUri(ConfigManager.getBaseUri())
                .when()
                .pathParam("editor", Editor.ADMIN)
                .header("Content-Type", ContentType.JSON)
                .body(RequestUtils.toMap(request))
                .delete("/player/delete/{editor}")
                .then()
                .statusCode(403);

        Assert.assertFalse(new PlayerQueryService().exists(request.getPlayerId()));
    }
}
