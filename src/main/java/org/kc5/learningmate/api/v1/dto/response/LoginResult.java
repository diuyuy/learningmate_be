package org.kc5.learningmate.api.v1.dto.response;

public record LoginResult(String accessToken, String refreshToken, MemberResponse memberResponse) {
}
