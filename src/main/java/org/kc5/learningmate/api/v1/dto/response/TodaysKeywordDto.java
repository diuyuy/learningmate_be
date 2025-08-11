package org.kc5.learningmate.api.v1.dto.response;

import lombok.Builder;
import lombok.Value;
import org.kc5.learningmate.domain.keyword.entity.TodaysKeyword;

import java.time.LocalDate;

/**
 * DTO for {@link org.kc5.learningmate.domain.keyword.entity.TodaysKeyword}
 */
@Value
@Builder
public class TodaysKeywordDto {
    Long id;
    KeywordDto keyword;
    LocalDate date;

    public static TodaysKeywordDto fromEntityWithKeyword(TodaysKeyword todaysKeyword) {
        return TodaysKeywordDto.builder()
                               .id(todaysKeyword.getId())
                               .keyword(KeywordDto.fromEntity(todaysKeyword.getKeyword()))
                               .date(todaysKeyword.getDate())
                               .build();
    }
}