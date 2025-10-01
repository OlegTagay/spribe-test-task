package com.spribe.tests.base;

import com.spribe.project.enums.Role;
import com.spribe.project.models.response.create.PlayerCreateResponse;
import com.spribe.project.models.response.get.PlayerItemResponse;
import com.spribe.project.service.player.PlayerDeleteService;
import com.spribe.project.service.player.PlayerFactory;
import com.spribe.project.service.player.PlayerQueryService;
import com.spribe.project.service.player.RandomPlayer;
import com.spribe.project.utils.StringUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;

import java.util.List;
import java.util.stream.Collectors;

public class BaseTest {
    protected static ThreadLocal<PlayerCreateResponse> randomPlayerResponse = new ThreadLocal<>();
    protected static ThreadLocal<PlayerCreateResponse> adminPlayerResponse = new ThreadLocal<>();

    @BeforeMethod
    public void beforeTest() {
        randomPlayerResponse.set(PlayerFactory.create(Role.USER));
        adminPlayerResponse.set(PlayerFactory.create(Role.ADMIN));
    }

    @AfterClass
    public void afterTest() {
        PlayerQueryService playerQueryService = new PlayerQueryService();
        List<Long> responseList = playerQueryService.getAll().stream()
                .filter(player -> player.getScreenName().startsWith(StringUtils.prefix))
                .map(PlayerItemResponse::getId)
                .collect(Collectors.toList());


        PlayerDeleteService.delete(responseList);
    }
}
