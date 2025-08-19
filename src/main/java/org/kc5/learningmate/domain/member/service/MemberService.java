package org.kc5.learningmate.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.kc5.learningmate.api.v1.dto.request.SignUpRequest;
import org.kc5.learningmate.common.exception.CommonException;
import org.kc5.learningmate.common.exception.ErrorCode;
import org.kc5.learningmate.domain.member.entity.Member;
import org.kc5.learningmate.domain.member.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void createMember(SignUpRequest signUpRequest) {
        //TODO: 이메일 전송 로직 필요
        String password = passwordEncoder.encode(signUpRequest.password());
        Member newMember = Member.builder()
                                 .email(signUpRequest.email())
                                 .passwordHash(password)
                                 .status(false)
                                 .build();
        memberRepository.save(newMember);
    }

    @Transactional(readOnly = true)
    public Long getMemberId(String email) {
        return memberRepository.findIdByEmail(email)
                               .orElseThrow(() -> new CommonException(ErrorCode.MEMBER_NOT_FOUND));

    }
}
