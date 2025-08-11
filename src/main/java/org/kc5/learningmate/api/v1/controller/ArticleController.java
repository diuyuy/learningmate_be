package org.kc5.learningmate.api.v1.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.kc5.learningmate.api.v1.dto.response.QuizResponse;
import org.kc5.learningmate.common.ResultResponse;
import org.kc5.learningmate.domain.article.service.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/v1/articles")
@RequiredArgsConstructor
@RestController
public class ArticleController {

    private final ArticleService articleService;

    @Operation(summary = "기사에 대한 퀴즈 조회", description = "특정 기사에 대해 작성한 퀴즈를 조회 합니다.")
    @GetMapping("/{articleId}/quizzes")
    public ResponseEntity<ResultResponse<List<QuizResponse>>> getQuiz(@PathVariable("articleId") Long articleId) {
        List<QuizResponse> response = articleService.getQuizList(articleId);
        return ResponseEntity
                .ok(new ResultResponse<>(response));
    }

}
