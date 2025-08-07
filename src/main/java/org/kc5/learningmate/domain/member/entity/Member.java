package org.kc5.learningmate.domain.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.kc5.learningmate.common.BaseEntity;

@Getter
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseEntity {
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "nickname", nullable = false, unique = true, length = 50)
    private String nickname;

    @Column(name = "password_hash", nullable = false, length = 60)
    private String passwordHash;

    @Column(name = "image_url")
    private String imageUrl;

    @ColumnDefault("1")
    @Column(nullable = false)
    private Boolean status;

}