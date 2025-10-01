package com.spribe.project.service.player;

import com.spribe.project.enums.Gender;
import com.spribe.project.enums.Role;
import com.spribe.project.models.response.create.PlayerCreateResponse;

public class PlayerFactory {

    public static PlayerCreateResponse create(Role role) {
        return switch (role) {
            case ADMIN -> AdminPlayer.createPlayer();
            case USER -> RandomPlayer.createPlayer();
            default -> throw new IllegalArgumentException("Unsupported role: " + role);
        };
    }
}
