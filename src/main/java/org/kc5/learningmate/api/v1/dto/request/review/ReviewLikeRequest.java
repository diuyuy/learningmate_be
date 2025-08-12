package org.kc5.learningmate.api.v1.dto.request.review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ReviewLikeRequest {

    private final Long memberId;
}
