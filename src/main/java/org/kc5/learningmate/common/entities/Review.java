package org.kc5.learningmate.common.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
public class Review {
    @Id
    @Column(name = "id", columnDefinition = "int UNSIGNED not null")
    private Long id;

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

    @NotNull
    @ColumnDefault("(now())")
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @NotNull
    @ColumnDefault("(now())")
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

}