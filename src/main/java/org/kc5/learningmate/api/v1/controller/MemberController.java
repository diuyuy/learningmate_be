package org.kc5.learningmate.api.v1.controller;

import lombok.RequiredArgsConstructor;
import org.kc5.learningmate.api.v1.dto.response.MemberResponse;
import org.kc5.learningmate.common.ResultResponse;
import org.kc5.learningmate.domain.auth.entity.MemberDetail;
import org.kc5.learningmate.domain.member.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
@RestController
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/me")
    public ResponseEntity<ResultResponse<MemberResponse>> getMember(@AuthenticationPrincipal MemberDetail memberDetail) {
        Long memberId = memberDetail.getMemberId();

        MemberResponse memberResponse = memberService.findMemberById(memberId);

        return ResponseEntity.ok()
                             .body(new ResultResponse<>(memberResponse));

    }
}
