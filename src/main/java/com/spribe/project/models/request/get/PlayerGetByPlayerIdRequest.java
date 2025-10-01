package com.spribe.project.models.request.get;

import java.util.Objects;

public class PlayerGetByPlayerIdRequest {
    private Long playerId;

    public PlayerGetByPlayerIdRequest() {
    }

    public PlayerGetByPlayerIdRequest(Long playerId) {
        this.playerId = playerId;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PlayerGetByPlayerIdRequest that = (PlayerGetByPlayerIdRequest) o;
        return Objects.equals(playerId, that.playerId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(playerId);
    }

    @Override
    public String toString() {
        return "PlayerGetByPlayerIdRequest{" +
                "playerId=" + playerId +
                '}';
    }
}
