package org.kc5.learningmate.domain.review.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.kc5.learningmate.common.BaseEntity;

@Getter
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Review extends BaseEntity {
    @NotNull
    @Lob
    @Column(name = "content_1", nullable = false)
    private String content1;

    @NotNull
    @Lob
    @Column(name = "content_2", nullable = false)
    private String content2;

    @NotNull
    @Lob
    @Column(name = "content_3", nullable = false)
    private String content3;

   }