package org.kc5.learningmate.domain.study.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.kc5.learningmate.common.BaseEntity;
import org.kc5.learningmate.domain.keyword.entity.Keyword;
import org.kc5.learningmate.domain.member.entity.Member;

@Getter
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Study extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "keyword_id")
    private Keyword keyword;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "study_stats", nullable = false)
    private Byte studyStats;

 }