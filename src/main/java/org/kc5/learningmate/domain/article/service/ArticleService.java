package org.kc5.learningmate.domain.article.service;

import lombok.RequiredArgsConstructor;
import org.kc5.learningmate.api.v1.dto.request.member.MemberQuizRequest;
import org.kc5.learningmate.api.v1.dto.response.ArticlePreviewResponse;
import org.kc5.learningmate.api.v1.dto.response.ArticleResponse;
import org.kc5.learningmate.api.v1.dto.response.QuizResponse;
import org.kc5.learningmate.common.exception.CommonException;
import org.kc5.learningmate.common.exception.ErrorCode;
import org.kc5.learningmate.domain.article.repository.ArticleRepository;
import org.kc5.learningmate.domain.keyword.service.KeywordService;
import org.kc5.learningmate.domain.member.entity.Member;
import org.kc5.learningmate.domain.member.repository.MemberRepository;
import org.kc5.learningmate.domain.quiz.entity.MemberQuiz;
import org.kc5.learningmate.domain.quiz.entity.Quiz;
import org.kc5.learningmate.domain.quiz.repository.MemberQuizRepository;
import org.kc5.learningmate.domain.quiz.repository.QuizRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final QuizRepository quizRepository;
    private final MemberQuizRepository memberQuizRepository;
    private final MemberRepository memberRepository;
    private final KeywordService keywordService;

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

    @Transactional(readOnly = true)
    public List<ArticlePreviewResponse> findArticlePreviewByKeywordId(Long keywordId) {
        keywordService.validateKeywordExists(keywordId);

        return articleRepository.findArticlePreviewByKeywordId(keywordId);
    }

    public void validateArticleExists(Long articleId) {
        if (!articleRepository.existsById(articleId))
            throw new CommonException(ErrorCode.ARTICLE_NOT_FOUND);
    }

    @Transactional
    public QuizResponse solveQuiz(Long articleId, Long quizId, MemberQuizRequest req, Long memberId) {

        Member member = memberRepository.findById(memberId)
                                        .orElseThrow(() ->
                                                new CommonException(ErrorCode.MEMBER_NOT_FOUND)
                                        );

        if (!articleRepository.existsById(articleId)) {
            throw new CommonException(ErrorCode.ARTICLE_NOT_FOUND);
        }

        Quiz quiz = quizRepository.findById(quizId)
                                  .orElseThrow(() -> new CommonException(ErrorCode.QUIZ_NOT_FOUND));

        boolean isExist = memberQuizRepository.existsSolved(quizId, memberId);

        boolean isCorrect = java.util.Objects.equals(quiz.getAnswer(), req.getMemberAnswer());

        if (!isExist) {
            MemberQuiz newMemberQuiz = MemberQuiz.builder()
                                                 .quiz(quiz)
                                                 .member(member)
                                                 .memberAnswer(req.getMemberAnswer())
                                                 .build();
            memberQuizRepository.save(newMemberQuiz);
        } else {
            memberQuizRepository.updateAnswer(quizId, memberId, req.getMemberAnswer());
        }

        return QuizResponse.from(quiz, isCorrect, req.getMemberAnswer());

    }
}
