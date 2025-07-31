package org.kc5.learningmate.common.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Keyword {
    @Id
    @Column(name = "id", columnDefinition = "int UNSIGNED not null")
    private Long id;

    @Size(max = 80)
    @NotNull
    @Column(name = "name", nullable = false, length = 80)
    private String name;

    @Size(max = 1320)
    @NotNull
    @Column(name = "description", nullable = false, length = 1320)
    private String description;

}