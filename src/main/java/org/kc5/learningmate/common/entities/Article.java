package org.kc5.learningmate.common.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "int UNSIGNED not null")
    private Long id;

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
    @Column(name = "content", nullable = false)
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
    private Instant publishedAt;

    @Size(max = 50)
    @NotNull
    @Column(name = "press", nullable = false, length = 50)
    private String press;

    @NotNull
    @Lob
    @Column(name = "summary", nullable = false)
    private String summary;

    @ColumnDefault("'0'")
    @Column(name = "views", columnDefinition = "int UNSIGNED not null")
    private Long views;

    @ColumnDefault("'0'")
    @Column(name = "scrap_count", columnDefinition = "int UNSIGNED not null")
    private Long scrapCount;

    @NotNull
    @ColumnDefault("(now())")
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @NotNull
    @ColumnDefault("(now())")
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

}