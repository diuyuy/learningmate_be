package org.kc5.learningmate.domain.article.service;

import lombok.RequiredArgsConstructor;
import org.kc5.learningmate.api.v1.dto.response.QuizResponse;
import org.kc5.learningmate.common.exception.CommonException;
import org.kc5.learningmate.common.exception.ErrorCode;
import org.kc5.learningmate.domain.article.repository.ArticleRepository;
import org.kc5.learningmate.domain.quiz.entity.Quiz;
import org.kc5.learningmate.domain.quiz.repository.QuizRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final QuizRepository quizRepository;

    @Transactional(readOnly = true)
    public List<QuizResponse> getQuizList(Long articleId) {
        articleRepository.findById(articleId)
                .orElseThrow(() -> new CommonException(ErrorCode.ARTICLE_NOT_FOUND));

        List<Quiz> quiz = quizRepository.findByArticleId(articleId);

        return QuizResponse.from(quiz);

    }

}
