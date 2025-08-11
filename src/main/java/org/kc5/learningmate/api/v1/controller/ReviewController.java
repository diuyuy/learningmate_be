package org.kc5.learningmate.api.v1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.kc5.learningmate.api.v1.dto.request.review.ReviewCreateRequest;
import org.kc5.learningmate.api.v1.dto.request.review.ReviewGetRequest;
import org.kc5.learningmate.api.v1.dto.request.review.ReviewUpdateRequest;
import org.kc5.learningmate.api.v1.dto.response.ReviewResponse;
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

    @Operation(summary = "기사 상세 내 리뷰 조회", description = "특정 기사에 대해 작성한 나의 리뷰를 조회 합니다.")
    @GetMapping("/articles/{articleId}/reviews/{memberId}") // 현재는 memberId를 PathVariable로 받지만, 로그인 기능 구현 후 제거 예정
    public ResponseEntity<ResultResponse<ReviewResponse>> getReview(@PathVariable("articleId") Long articleId,
                                                                    @PathVariable("memberId") Long memberId) {
        ReviewResponse response = reviewService.getReview(articleId, memberId);
        return ResponseEntity
                .ok(new ResultResponse<>(response));
    }

    @Operation(summary = "기사 상세 리뷰 수정", description = "한 개의 기사에 대해 리뷰를 수정 합니다.")
    @PatchMapping("/articles/{articleId}/reviews/{reviewId}")
    public  ResponseEntity<ResultResponse<Void>> updateReview(@PathVariable("articleId") Long articleId,
                                             @PathVariable("reviewId") Long reviewId,
                                             @Valid @RequestBody ReviewUpdateRequest request) {
        reviewService.updateReview(articleId, reviewId, request);
        return ResponseEntity
                .ok(new ResultResponse<>());
    }

    @Operation(summary = "기사 상세 리뷰 삭제", description = "한 개의 기사에 대해 리뷰를 삭제 합니다.")
    @DeleteMapping("/articles/{articleId}/reviews/{reviewId}")
    public  ResponseEntity<ResultResponse<Void>> createReview(@PathVariable("articleId") Long articleId,
                                             @PathVariable("reviewId") Long reviewId) {
        reviewService.deleteReview(articleId, reviewId);
        return ResponseEntity
                .ok(new ResultResponse<>());
    }

//    @Operation(summary = "swagger 테스트!!!!", description = "특정 기사에 대해 작성한 나의 리뷰를 조회 합니다.")
//    @GetMapping("/articles/{articleId}/reviews")
//    public ResponseEntity<ReviewResponse> getReview2(@PathVariable("articleId") Long articleId,
//                                                     @RequestBody ReviewGetRequest request) {
//        ReviewResponse response = reviewService.getReview(articleId, request.getMemberId());
//        return ResponseEntity
//                .ok(response);
//    }

}
