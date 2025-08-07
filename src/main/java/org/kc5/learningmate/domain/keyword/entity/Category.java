package org.kc5.learningmate.domain.keyword.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.kc5.learningmate.common.BaseEntity;

@Getter
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Category extends BaseEntity {
    @Column(name = "name", nullable = false, length = 2)
    private String name;
}