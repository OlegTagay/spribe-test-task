package com.spribe.project.assertions;

import com.spribe.project.models.request.create.PlayerCreateRequest;
import com.spribe.project.models.response.create.PlayerCreateResponse;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class PlayerAssertions {
    public static void assertMatches(PlayerCreateRequest request, PlayerCreateResponse response) {
        assertEquals(response.getAge().toString(), request.getAge());
        assertEquals(response.getGender(), request.getGender().getValue());
        assertEquals(response.getLogin(), request.getLogin());
        assertEquals(response.getRole(), request.getRole().name());
        assertEquals(response.getScreenName(), request.getScreenName());
        assertNotNull(response.getId(), "Id should be assigned");
    }
}
