package com.spribe.tests.player;

import com.spribe.project.config.ConfigManager;
import com.spribe.project.enums.Editor;
import com.spribe.project.models.common.HttpStatus;
import com.spribe.project.models.request.get.PlayerGetByPlayerIdRequest;
import com.spribe.project.models.response.get.PlayerItemResponse;
import com.spribe.project.service.player.PlayerQueryService;
import com.spribe.project.utils.RequestUtils;
import com.spribe.tests.base.BaseTest;
import io.qameta.allure.Issue;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.testng.Tag;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

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
                .statusCode(HttpStatus.NO_CONTENT.code());

        Assert.assertFalse(new PlayerQueryService().exists(request.getPlayerId()));
    }

    @Severity(SeverityLevel.CRITICAL)
    @Tag("Smoke")
    @Tag("Delete")
    @Test(description = "Delete user should return 403")
    @Issue("Invalid editor is not allowed to delete players")
    @Ignore
    public void deleteNonExistingUser_shouldReturn403() {
        PlayerGetByPlayerIdRequest request = new PlayerGetByPlayerIdRequest(randomPlayerResponse.get().getId());

        given()
                .baseUri(ConfigManager.getBaseUri())
                .when()
                .pathParam("editor", Editor.INVALID)
                .header("Content-Type", ContentType.JSON)
                .body(RequestUtils.toMap(request))
                .delete("/player/delete/{editor}")
                .then()
                .statusCode(HttpStatus.FORBIDDEN.code());

        Assert.assertFalse(new PlayerQueryService().exists(request.getPlayerId()));
    }

    @Severity(SeverityLevel.CRITICAL)
    @Tag("Smoke")
    @Tag("Delete")
    @Test(description = "Delete user should return 403")
    public void deleteWithInvalidEditor_shouldReturn403() {
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
                .statusCode(HttpStatus.FORBIDDEN.code());

        Assert.assertFalse(new PlayerQueryService().exists(request.getPlayerId()));
    }
}
