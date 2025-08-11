package org.kc5.learningmate.api.v1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.kc5.learningmate.api.v1.dto.request.review.ReviewCreateRequest;
import org.kc5.learningmate.api.v1.dto.request.review.ReviewGetRequest;
import org.kc5.learningmate.api.v1.dto.response.ReviewResponse;
import org.kc5.learningmate.common.ResultResponse;
import org.kc5.learningmate.domain.review.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1")
@RequiredArgsConstructor
@RestController
@Tag(name = "Review Controller", description = "리뷰 관련 API")
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(summary = "기사 상세 리뷰 작성", description = "한 개의 기사에 대해 리뷰를 작성합니다.")
    @PostMapping("/articles/{articleId}/reviews")
    public ResultResponse<Void> createReview(@PathVariable("articleId") Long articleId,
                                             @Valid @RequestBody ReviewCreateRequest request) {
        reviewService.createReview(articleId, request);
        return new ResultResponse<>(HttpStatus.CREATED);
    }

    @Operation(summary = "기사 상세 내 리뷰 조회", description = "특정 기사에 대해 작성한 나의 리뷰를 조회 합니다.")
    @GetMapping("/articles/{articleId}/reviews")
    public ResultResponse<ReviewResponse> getReview(@PathVariable("articleId") Long articleId,
                                                    @RequestBody ReviewGetRequest request) {
        ReviewResponse response = reviewService.getReview(articleId, request.getMemberId());
        return new ResultResponse<>(response);
    }

}
