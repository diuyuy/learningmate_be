package org.kc5.learningmate.domain.auth.service;

import lombok.RequiredArgsConstructor;
import org.kc5.learningmate.api.v1.dto.request.LoginRequest;
import org.kc5.learningmate.domain.auth.provider.JwtTokenProvider;
import org.kc5.learningmate.domain.member.service.MemberService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final MemberService memberService;

    public String signInByEmailPwd(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.email(),
                        loginRequest.password()));


        Long memberId = memberService.getMemberId(loginRequest.email());
        return jwtTokenProvider.generateToken(authentication, memberId);
    }
}
