package org.kc5.learningmate.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.kc5.learningmate.api.v1.dto.request.auth.SignUpRequest;
import org.kc5.learningmate.api.v1.dto.response.MemberResponse;
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
        String password = passwordEncoder.encode(signUpRequest.password());
        Member newMember = Member.builder()
                                 .email(signUpRequest.email())
                                 .passwordHash(password)
                                 .status(true)
                                 .build();
        memberRepository.save(newMember);
    }

    @Transactional(readOnly = true)
    public MemberResponse findMemberById(Long memberId) {
        return memberRepository.findById(memberId)
                               .map(MemberResponse::from)
                               .orElseThrow(() -> new CommonException(ErrorCode.MEMBER_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public MemberResponse findMemberByEmail(String email) {
        return memberRepository.findByEmail(email)
                               .map(MemberResponse::from)
                               .orElseThrow(() -> new CommonException(ErrorCode.MEMBER_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public boolean checkEmailExists(String email) {
        return memberRepository.existsByEmail(email);
    }

}
