package com.spribe.project.models.response.get;

import java.util.List;
import java.util.Objects;

public class GetAllPlayersResponse {
    private List<PlayerItemResponse> players;

    public List<PlayerItemResponse> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerItemResponse> players) {
        this.players = players;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        GetAllPlayersResponse that = (GetAllPlayersResponse) o;
        return Objects.equals(players, that.players);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(players);
    }

    @Override
    public String toString() {
        return "GetAllPlayersResponse{" +
                "players=" + players +
                '}';
    }
}
