package org.kc5.learningmate.common.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Quiz extends  BaseEntity {
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;

    @Size(max = 255)
    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

    @Size(max = 255)
    @NotNull
    @Column(name = "question_1", nullable = false)
    private String question1;

    @Size(max = 255)
    @NotNull
    @Column(name = "question_2", nullable = false)
    private String question2;

    @Size(max = 255)
    @NotNull
    @Column(name = "question_3", nullable = false)
    private String question3;

    @Size(max = 255)
    @NotNull
    @Column(name = "question_4", nullable = false)
    private String question4;

    @NotNull
    @Column(name = "answer", nullable = false)
    private Byte answer;

    @Size(max = 512)
    @NotNull
    @Column(name = "explanation", nullable = false, length = 512)
    private String explanation;

}