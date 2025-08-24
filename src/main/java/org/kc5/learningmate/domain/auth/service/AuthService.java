package org.kc5.learningmate.domain.auth.service;

import lombok.RequiredArgsConstructor;
import org.kc5.learningmate.api.v1.dto.request.LoginRequest;
import org.kc5.learningmate.api.v1.dto.response.LoginResult;
import org.kc5.learningmate.api.v1.dto.response.MemberResponse;
import org.kc5.learningmate.api.v1.dto.response.TokenResponse;
import org.kc5.learningmate.common.exception.CommonException;
import org.kc5.learningmate.common.exception.ErrorCode;
import org.kc5.learningmate.domain.auth.provider.JwtTokenProvider;
import org.kc5.learningmate.domain.member.service.MemberService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final MemberService memberService;
    private final RefreshTokenService refreshTokenService;

    public LoginResult signInByEmailPwd(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.email(),
                            loginRequest.password()));

            String email = authentication.getName();
            MemberResponse memberResponse = memberService.findMemberByEmail(email);

            String accessToken = jwtTokenProvider.generateToken(memberResponse.id());
            String refreshToken = refreshTokenService.generateRefreshToken(memberResponse.id());

            return new LoginResult(accessToken, refreshToken, memberResponse);

        } catch (Exception e) {
            throw new CommonException(ErrorCode.UNAUTHORIZED);
        }

    }

    public TokenResponse refreshToken(String refreshToken) {
        Long memberId = refreshTokenService.validateRefreshToken(refreshToken);

        String accessToken = jwtTokenProvider.generateToken(memberId);
        refreshTokenService.deleteRefreshToken(refreshToken);
        String newRefreshToken = refreshTokenService.generateRefreshToken(memberId);

        return new TokenResponse(accessToken, newRefreshToken);
    }
}
