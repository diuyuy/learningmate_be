package org.kc5.learningmate.domain.auth.service;

import lombok.RequiredArgsConstructor;
import org.kc5.learningmate.domain.auth.entity.MemberDetail;
import org.kc5.learningmate.domain.member.repository.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
class MemberDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername: " + username);
        Optional<MemberDetail> member = memberRepository.findMemberDetailByEmail(username);

        return member.orElseThrow(() -> new UsernameNotFoundException("해당 이메일을 가진 사용자가 존재하지 않습니다"));
    }
}
