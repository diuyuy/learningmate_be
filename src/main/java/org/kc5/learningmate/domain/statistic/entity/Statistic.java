package org.kc5.learningmate.domain.statistic.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.kc5.learningmate.common.BaseEntity;
import org.kc5.learningmate.domain.keyword.entity.Category;
import org.kc5.learningmate.domain.member.entity.Member;

@Getter
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Statistic extends BaseEntity {
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ColumnDefault("'0'")
    @Column(name = "study_count", nullable = false)
    private Long studyCount;

}