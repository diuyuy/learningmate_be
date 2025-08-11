package org.kc5.learningmate.api.v1.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.kc5.learningmate.domain.quiz.entity.Quiz;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
@Schema(description = "퀴즈 조회 응답 모델")
public class QuizResponse {

    @Schema(description = "기사 제목")
    private String articleTitle;

    @Schema(description = "퀴즈 문제")
    private String description;

    @Schema(description = "퀴즈 보기 1")
    private String question1;

    @Schema(description = "퀴즈 보기 2")
    private String question2;

    @Schema(description = "퀴즈 보기 3")
    private String question3;

    @Schema(description = "퀴즈 보기 4")
    private String question4;

    @Schema(description = "답")
    private String answer;

    @Schema(description = "해설")
    private String explanation;

    public static List<QuizResponse> from(List<Quiz> quizzes) {
        return quizzes.stream()
                .map(q -> QuizResponse.builder()
                        .articleTitle(q.getArticle().getTitle())
                        .description(q.getDescription())
                        .question1(q.getQuestion1())
                        .question2(q.getQuestion2())
                        .question3(q.getQuestion3())
                        .question4(q.getQuestion4())
                        .answer(q.getAnswer())
                        .explanation(q.getExplanation())
                        .build()
                )
                .toList();
    }

}
