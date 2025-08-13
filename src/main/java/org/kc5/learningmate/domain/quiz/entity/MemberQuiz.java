package org.kc5.learningmate.domain.quiz.entity;

import jakarta.persistence.*;
import lombok.*;
import org.kc5.learningmate.common.BaseEntity;
import org.kc5.learningmate.domain.member.entity.Member;

@Getter
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberQuiz extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "member_answer", nullable = false, length = 1)
    private String memberAnswer;

}