package org.kc5.learningmate.domain.article.service;

import lombok.RequiredArgsConstructor;
import org.kc5.learningmate.api.v1.dto.response.ArticleResponse;
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
        if (!articleRepository.existsById(articleId)) {
            throw new CommonException(ErrorCode.ARTICLE_NOT_FOUND);
        }

        List<Quiz> quizList = quizRepository.findByArticleId(articleId);

        if (quizList.isEmpty()) {
            throw new CommonException(ErrorCode.QUIZ_LIST_NOT_FOUND);
        }

        return QuizResponse.from(quizList);

    }

    @Transactional(readOnly = true)
    public ArticleResponse findById(Long articleId) {
        return articleRepository.findById(articleId)
                                .map(ArticleResponse::from)
                                .orElseThrow(() -> new CommonException(ErrorCode.ARTICLE_NOT_FOUND));
    }

    public void validateArticleExists(Long articleId) {
        if (!articleRepository.existsById(articleId))
            throw new CommonException(ErrorCode.ARTICLE_NOT_FOUND);
    }

}
