package org.kc5.learningmate.domain.quiz.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.kc5.learningmate.domain.article.entity.Article;
import org.kc5.learningmate.common.BaseEntity;

@Getter
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Quiz extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "question_1", nullable = false)
    private String question1;

    @Column(name = "question_2", nullable = false)
    private String question2;

    @Column(name = "question_3", nullable = false)
    private String question3;

    @Column(name = "question_4", nullable = false)
    private String question4;

    @Column(name = "answer", nullable = false, length = 1)
    private String answer;

    @Column(name = "explanation", nullable = false, length = 512)
    private String explanation;

}