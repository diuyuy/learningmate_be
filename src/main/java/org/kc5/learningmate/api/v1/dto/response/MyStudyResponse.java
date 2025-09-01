package org.kc5.learningmate.api.v1.dto.response;


import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record MyStudyResponse(
        @Schema(description = "study ID") Long id,
        @Schema(description = "학습 키워드") Long keywordId,
        @Schema(description = "학습 상태") Byte studyStats,
        @Schema(description = "학습 상태 카운트") int studyStatusCount,
        @Schema(description = "처음 으로 공부 한 날짜") LocalDateTime createdAt,
        @Schema(description = "마지막 으로 공부 한 날짜") LocalDateTime updatedAt
) {

    public static MyStudyResponse of(Long id, Long keywordId, Byte studyStats,
                                     LocalDateTime createdAt, LocalDateTime updatedAt) {
        int count = (studyStats == null) ? 0 : Integer.bitCount(studyStats);
        return new MyStudyResponse(id, keywordId, studyStats, count, createdAt, updatedAt);
    }
}
