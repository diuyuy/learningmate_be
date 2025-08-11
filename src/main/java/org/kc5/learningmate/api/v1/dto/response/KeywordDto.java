package org.kc5.learningmate.api.v1.dto.response;

import lombok.Builder;
import lombok.Value;
import org.kc5.learningmate.domain.keyword.entity.Keyword;

import java.io.Serializable;

/**
 * DTO for {@link org.kc5.learningmate.domain.keyword.entity.Keyword}
 */
@Value
@Builder
public class KeywordDto implements Serializable {
    Long id;
    String name;
    String description;

    public static KeywordDto fromEntity(Keyword keyword) {
        return KeywordDto.builder()
                         .id(keyword.getId())
                         .name(keyword.getName())
                         .description(keyword.getDescription())
                         .build();
    }
}