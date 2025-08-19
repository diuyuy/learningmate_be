package org.kc5.learningmate.api.v1.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.kc5.learningmate.api.v1.dto.request.LoginRequest;
import org.kc5.learningmate.api.v1.dto.request.SignUpRequest;
import org.kc5.learningmate.common.ResultResponse;
import org.kc5.learningmate.domain.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@RestController
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final MemberService memberService;

    @PostMapping("/login")
    public ResponseEntity<String> signInByEmailPwd(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.email(),
                        loginRequest.password()));

        if (authentication.isAuthenticated()) {
            return ResponseEntity.ok(authentication.getName());
        }

        return ResponseEntity.notFound()
                             .build();
    }

    @PostMapping("/sign-up")
    ResponseEntity<ResultResponse<Void>> signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        memberService.createMember(signUpRequest);

        return ResponseEntity.ok()
                             .body(new ResultResponse<>(HttpStatus.OK));
    }
}
