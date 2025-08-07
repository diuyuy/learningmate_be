package org.kc5.learningmate.domain.article.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.Internal;
import org.hibernate.annotations.ColumnDefault;
import org.kc5.learningmate.common.BaseEntity;
import org.kc5.learningmate.domain.keyword.entity.Keyword;

import java.time.LocalDateTime;

@Getter
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Article extends BaseEntity {
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "keyword_id", nullable = false)
    private Keyword keyword;

    @Size(max = 255)
    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @NotNull
    @Lob
    @Column(name = "content", columnDefinition = "TEXT", nullable = false)
    private String content;

    @Size(max = 255)
    @NotNull
    @Column(name = "link", nullable = false)
    private String link;

    @Size(max = 100)
    @NotNull
    @Column(name = "reporter", nullable = false, length = 100)
    private String reporter;

    @NotNull
    @Column(name = "published_at", nullable = false)
    private LocalDateTime publishedAt;

    @Size(max = 50)
    @NotNull
    @Column(name = "press", nullable = false, length = 50)
    private String press;

    @NotNull
    @Lob
    @Column(name = "summary", columnDefinition = "TEXT", nullable = false)
    private String summary;

    @ColumnDefault("'0'")
    @Column(name = "views")
    private Long views;

    @ColumnDefault("'0'")
    @Column(name = "scrap_count",nullable = false)
    private Long scrapCount;
}