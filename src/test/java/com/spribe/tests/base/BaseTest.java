package com.spribe.tests.base;

import com.spribe.project.enums.Role;
import com.spribe.project.models.response.create.PlayerCreateResponse;
import com.spribe.project.service.player.PlayerFactory;
import com.spribe.project.service.player.RandomPlayer;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected static ThreadLocal<PlayerCreateResponse> randomPlayerResponse = new ThreadLocal<>();
    protected static ThreadLocal<PlayerCreateResponse> adminPlayerResponse = new ThreadLocal<>();

    @BeforeMethod
    public void beforeTest() {
        randomPlayerResponse.set(PlayerFactory.create(Role.USER));
        adminPlayerResponse.set(PlayerFactory.create(Role.ADMIN));
    }
}
