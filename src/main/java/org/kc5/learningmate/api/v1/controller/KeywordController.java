package org.kc5.learningmate.api.v1.controller;

import lombok.RequiredArgsConstructor;
import org.kc5.learningmate.api.v1.dto.response.*;
import org.kc5.learningmate.common.ResultResponse;
import org.kc5.learningmate.domain.article.service.ArticleService;
import org.kc5.learningmate.domain.keyword.service.KeywordService;
import org.kc5.learningmate.domain.review.service.ReviewService;
import org.kc5.learningmate.domain.study.service.VideoService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequestMapping("/api/v1/keywords")
@RequiredArgsConstructor
@RestController
public class KeywordController {
    private final KeywordService keywordService;
    private final VideoService videoService;
    private final ArticleService articleService;
    private final ReviewService reviewService;

    @GetMapping()
    public ResponseEntity<ResultResponse<List<TodaysKeywordResponse>>> findAllWithKeywords(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        List<TodaysKeywordResponse> todaysKeywordDtoList = keywordService.findByPeriodWithKeywords(startDate, endDate);

        return ResponseEntity.ok()
                             .body(new ResultResponse<>(HttpStatus.OK, todaysKeywordDtoList));
    }

    @GetMapping("/{keywordId}")
    public ResponseEntity<ResultResponse<KeywordResponse>> findById(@PathVariable Long keywordId) {
        KeywordResponse keywordDto = keywordService.findById(keywordId);
        return ResponseEntity.ok()
                             .body(new ResultResponse<>(HttpStatus.OK, keywordDto));
    }

    @GetMapping("/{keywordId}/videos")
    public ResponseEntity<ResultResponse<VideoResponse>> findVideoByKeywordId(@PathVariable Long keywordId) {
        VideoResponse videoResponse = videoService.findByKeywordId(keywordId);
        return ResponseEntity.ok()
                             .body(new ResultResponse<>(HttpStatus.OK, videoResponse));
    }

    @GetMapping("/{keywordId}/articles")
    public ResponseEntity<ResultResponse<List<ArticlePreviewResponse>>> findArticlePreviewByKeywordId(@PathVariable Long keywordId) {
        List<ArticlePreviewResponse> articlePreviewResponses = articleService.findArticlePreviewByKeywordId(keywordId);

        return ResponseEntity.ok()
                             .body(new ResultResponse<>(HttpStatus.OK, articlePreviewResponses));
    }

    @GetMapping("/{keywordId}/reviews")
    public ResponseEntity<ResultResponse<List<ReviewResponse>>> findReviewsByKeywordId(@PathVariable Long keywordId, Pageable pageable) {
        List<ReviewResponse> reviews = reviewService.getReviewsByKeywordId(keywordId, pageable);

        return ResponseEntity.ok()
                             .body(new ResultResponse<>(HttpStatus.OK, reviews));
    }
}
