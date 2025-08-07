package org.kc5.learningmate.domain.keyword.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.kc5.learningmate.common.BaseEntity;

import java.time.LocalDate;

@Getter
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class TodaysKeyword extends BaseEntity {
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "keyword_id", nullable = false)
    private Keyword keyword;

    @NotNull
    @Column(name = "date", nullable = false, unique = true)
    private LocalDate date;

}