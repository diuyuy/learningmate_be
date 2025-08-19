package org.kc5.learningmate.domain.member.repository;

import org.kc5.learningmate.api.v1.dto.response.MemberDetailResponse;
import org.kc5.learningmate.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query("""
                select new org.kc5.learningmate.api.v1.dto.response.MemberDetailResponse(
                       m.email, m.passwordHash
                    )
                    from Member m
                    where m.email = :email
            """)
    Optional<MemberDetailResponse> findMemberDetailByEmail(@Param("email") String email);
}
