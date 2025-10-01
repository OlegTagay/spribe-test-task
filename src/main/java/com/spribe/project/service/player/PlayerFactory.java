package com.spribe.project.service.player;

import com.spribe.project.models.response.create.PlayerCreateResponse;

public abstract class PlayerFactory {
    public abstract PlayerCreateResponse createPlayer();
}
