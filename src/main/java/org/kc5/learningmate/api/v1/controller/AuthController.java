package org.kc5.learningmate.api.v1.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.kc5.learningmate.api.v1.dto.request.LoginRequest;
import org.kc5.learningmate.api.v1.dto.request.SignUpRequest;
import org.kc5.learningmate.api.v1.dto.response.LoginResult;
import org.kc5.learningmate.api.v1.dto.response.MemberResponse;
import org.kc5.learningmate.api.v1.dto.response.TokenResponse;
import org.kc5.learningmate.common.ResultResponse;
import org.kc5.learningmate.domain.auth.provider.HttpCookieProvider;
import org.kc5.learningmate.domain.auth.service.AuthService;
import org.kc5.learningmate.domain.member.service.MemberService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@RestController
public class AuthController {
    private final AuthService authService;
    private final MemberService memberService;
    private final HttpCookieProvider httpCookieProvider;

    @PostMapping("/sign-in")
    public ResponseEntity<ResultResponse<MemberResponse>> signInByEmailPwd(@Valid @RequestBody LoginRequest loginRequest) {
        LoginResult loginResult = authService.signInByEmailPwd(loginRequest);

        ResponseCookie responseCookie = httpCookieProvider.generateAccessTokenCookie(loginResult.accessToken());

        return ResponseEntity.ok()
                             .header(HttpHeaders.SET_COOKIE, responseCookie.toString())
                             .body(new ResultResponse<>(loginResult.memberResponse()));
    }

    @PostMapping("/sign-up")
    ResponseEntity<ResultResponse<Void>> signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        memberService.createMember(signUpRequest);

        return ResponseEntity.ok()
                             .body(new ResultResponse<>(HttpStatus.OK));
    }

    @PostMapping("/sign-out")
    ResponseEntity<ResultResponse<Void>> logout() {
        ResponseCookie logoutAccessTokenCookie = httpCookieProvider.createSignOutCookie("accessToken");

        ResponseCookie logoutRefreshTokenCookie = httpCookieProvider.createSignOutCookie("refreshToken");

        return ResponseEntity.ok()
                             .header(HttpHeaders.SET_COOKIE, logoutAccessTokenCookie.toString())
                             .header(HttpHeaders.SET_COOKIE, logoutRefreshTokenCookie.toString())
                             .body(new ResultResponse<>(HttpStatus.OK));
    }

    @PostMapping("/refresh-token")
    ResponseEntity<ResultResponse<Void>> refreshToken(HttpServletRequest request) {
        String refreshToken = httpCookieProvider.getRefreshToken(request);

        TokenResponse tokenResponse = authService.refreshToken(refreshToken);

        ResponseCookie accessTokenCookie = httpCookieProvider.generateAccessTokenCookie(tokenResponse.accessToken());

        ResponseCookie refreshTokenCookie = httpCookieProvider.generateRefreshTokenCookie(tokenResponse.refreshTokens());


        return ResponseEntity.ok()
                             .header(HttpHeaders.SET_COOKIE, accessTokenCookie.toString())
                             .header(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString())
                             .body(new ResultResponse<>(HttpStatus.OK));
    }
}
