package com.spribe.project.service.player;

import com.spribe.project.config.ConfigManager;
import com.spribe.project.enums.Editor;
import com.spribe.project.models.common.HttpStatus;
import com.spribe.project.models.request.get.PlayerGetByPlayerIdRequest;
import com.spribe.project.utils.RequestUtils;
import io.restassured.http.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.event.WindowFocusListener;
import java.util.List;

import static io.restassured.RestAssured.given;

public class PlayerDeleteService {
    private static final Logger log = LoggerFactory.getLogger(PlayerDeleteService.class);

    public static void delete(Long playerId) {
        log.info("Start delete playerId: " + playerId);
        PlayerGetByPlayerIdRequest request = new PlayerGetByPlayerIdRequest(playerId);

        given()
                .baseUri(ConfigManager.getBaseUri())
                .when()
                .pathParam("editor", Editor.ADMIN)
                .header("Content-Type", ContentType.JSON)
                .body(RequestUtils.toMap(request))
                .delete("/player/delete/{editor}")
                .then()
                .statusCode(HttpStatus.NO_CONTENT.code());

        log.info("Finish delete");
    }

    public static void delete(List<Long> playerIds) {
        int retry = 0;
        log.info("Start deleting list of playerIds");
        while (retry <= 3) {
            try {
                for (Long id : playerIds) {
                    delete(id);
                }
                // ✅ success, break loop
                break;
            } catch (Exception e) {
                log.warn("Delete attempt {} failed.", retry + 1, e);
                retry++;
                if (retry > 3) {
                    throw e; // rethrow or wrap if needed
                }
            }
        }
        log.info("Finish deleting list of playerIds");
    }
}
