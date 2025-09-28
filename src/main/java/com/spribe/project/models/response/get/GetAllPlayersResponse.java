package com.spribe.project.models.response.get;

import java.util.List;
import java.util.Objects;

public class GetAllPlayersResponse {
    private List<PlayerItemResponse> playerItemResponseList;

    public List<PlayerItemResponse> getPlayerItemResponseList() {
        return playerItemResponseList;
    }

    public void setPlayerItemResponseList(List<PlayerItemResponse> playerItemResponseList) {
        this.playerItemResponseList = playerItemResponseList;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        GetAllPlayersResponse that = (GetAllPlayersResponse) o;
        return Objects.equals(playerItemResponseList, that.playerItemResponseList);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(playerItemResponseList);
    }

    @Override
    public String toString() {
        return "GetAllPlayersResponse{" +
                "playerItemResponseList=" + playerItemResponseList +
                '}';
    }
}
