package org.kc5.learningmate.domain.member.repository;

import org.kc5.learningmate.domain.auth.entity.MemberDetail;
import org.kc5.learningmate.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query("""
                select new org.kc5.learningmate.domain.auth.entity.MemberDetail(
                       m.id, m.email, m.passwordHash
                    )
                    from Member m
                    where m.email = :email
            """)
    Optional<MemberDetail> findMemberDetailByEmail(@Param("email") String email);

    @Query("select id from Member where email = :email")
    Optional<Long> findIdByEmail(@Param("email") String email);

    boolean existsByEmail(String email);

    Optional<Member> findByEmail(String email);

    boolean existsByNickname(String nickname);
}
