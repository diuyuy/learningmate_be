package org.kc5.learningmate.api.v1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.kc5.learningmate.api.v1.dto.request.review.ReviewCreateRequest;
import org.kc5.learningmate.api.v1.dto.request.review.ReviewLikeRequest;
import org.kc5.learningmate.api.v1.dto.request.review.ReviewUpdateRequest;
import org.kc5.learningmate.api.v1.dto.response.MyReviewResponse;
import org.kc5.learningmate.common.ResultResponse;
import org.kc5.learningmate.domain.review.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1")
@RequiredArgsConstructor
@RestController
@Tag(name = "Review Controller", description = "리뷰 관련 API")
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(summary = "기사 상세 리뷰 작성", description = "한 개의 기사에 대해 리뷰를 작성합니다.")
    @PostMapping("/articles/{articleId}/reviews")
    public ResponseEntity<ResultResponse<Void>> createReview(@PathVariable("articleId") Long articleId,
                                                             @Valid @RequestBody ReviewCreateRequest request) {
        reviewService.createReview(articleId, request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResultResponse<>(HttpStatus.CREATED));
    }

    // TODO: 현재는 memberId를 PathVariable로 받지만, 로그인 기능 구현 후 제거 예정
    @Operation(summary = "기사 상세 내 리뷰 조회", description = "특정 기사에 대해 작성한 나의 리뷰를 조회 합니다.")
    @GetMapping("/articles/{articleId}/reviews/{memberId}")
    public ResponseEntity<ResultResponse<MyReviewResponse>> getReview(@PathVariable("articleId") Long articleId,
                                                                      @PathVariable("memberId") Long memberId) {
        MyReviewResponse response = reviewService.getReview(articleId, memberId);
        return ResponseEntity
                .ok(new ResultResponse<>(response));
    }

    @Operation(summary = "기사 상세 리뷰 수정", description = "한 개의 기사에 대해 리뷰를 수정 합니다.")
    @PatchMapping("/articles/{articleId}/reviews/{reviewId}")
    public ResponseEntity<ResultResponse<MyReviewResponse>> updateReview(@PathVariable("articleId") Long articleId,
                                                             @PathVariable("reviewId") Long reviewId,
                                                             @Valid @RequestBody ReviewUpdateRequest request) {
        MyReviewResponse response = reviewService.updateReview(articleId, reviewId, request);
        return ResponseEntity
                .ok(new ResultResponse<>(response));
    }

    // TODO: 로그인 구현 후, 인증 사용자(memberId)가 해당 리뷰의 작성자인지 검증
    @Operation(summary = "기사 상세 리뷰 삭제", description = "한 개의 기사에 대해 리뷰를 삭제 합니다.")
    @DeleteMapping("/articles/{articleId}/reviews/{reviewId}")
    public ResponseEntity<ResultResponse<Void>> createReview(@PathVariable("articleId") Long articleId,
                                                             @PathVariable("reviewId") Long reviewId) {
        reviewService.deleteReview(articleId, reviewId);
        return ResponseEntity
                .ok(new ResultResponse<>());
    }

    // TODO: 현재는 memberId를 RequestBody로 받지만, 로그인 기능 구현 후 제거 예정
    @Operation(summary = "리뷰 좋아요", description = "한 개의 리뷰에 대해 좋아요 합니다.")
    @PostMapping("/reviews/{reviewId}/likes")
    public ResponseEntity<ResultResponse<Void>> likeReview(@PathVariable("reviewId") Long reviewId,
                                                           @RequestBody ReviewLikeRequest req) {
        reviewService.likeReview(reviewId, req.getMemberId());
        return ResponseEntity
                .ok(new ResultResponse<>());
    }

    // TODO: 현재는 memberId를 RequestBody로 받지만, 로그인 기능 구현 후 제거 예정
    @Operation(summary = "리뷰 좋아요 취소", description = "한 개의 리뷰에 대해 좋아요 취소 합니다.")
    @DeleteMapping("/reviews/{reviewId}/likes")
    public ResponseEntity<ResultResponse<Void>> unlikeReview(@PathVariable("reviewId") Long reviewId,
                                                             @RequestBody ReviewLikeRequest req) {
        reviewService.unlikeReview(reviewId, req.getMemberId());
        return ResponseEntity
                .ok(new ResultResponse<>());
    }
}
