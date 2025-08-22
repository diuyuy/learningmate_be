package org.kc5.learningmate.api.v1.dto.response;

public record LoginResult(String accessToken, MemberResponse memberResponse) {
    public static LoginResult from(String accessToken, MemberResponse memberResponse) {
        return new LoginResult(accessToken, memberResponse);
    }
}
