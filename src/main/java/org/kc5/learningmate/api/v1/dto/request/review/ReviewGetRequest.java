package org.kc5.learningmate.api.v1.dto.request.review;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ReviewGetRequest {

    // 로그인 구현시 삭제 예정
    @NotNull(message = "member id는 null이 될 수 없습니다.")
    @Schema(description = "회원 ID", example = "1")
    private Long memberId;
}
