package org.kc5.learningmate.domain.auth.provider;

import lombok.RequiredArgsConstructor;
import org.kc5.learningmate.domain.auth.properties.AuthProperties;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HttpCookieProvider {
    private final AuthProperties authProperties;

    public ResponseCookie generateCookie(String accessToken) {
        long maxAge = authProperties.getExpirationMills() / 1000;
        return ResponseCookie.from("accessToken", accessToken)
                             .httpOnly(true)
                             .path("/")
                             .sameSite(authProperties.getSameSite())
                             .secure(true)
                             .maxAge(maxAge)
                             .build();
    }

    public ResponseCookie createSignOutCookie(String cookieName) {
        // 로그인할 때 보냈던 쿠키와 path, sameSite 등 속성이 모두 똑같아야지 브라우저의 쿠키가 업데이트 됨.
        return ResponseCookie.from(cookieName, "")
                             .httpOnly(true)
                             .path("/")
                             .sameSite(authProperties.getSameSite())
                             .secure(true)
                             .maxAge(0)
                             .build();
    }
}
