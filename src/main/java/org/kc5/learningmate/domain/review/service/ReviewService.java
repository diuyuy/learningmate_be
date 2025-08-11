package org.kc5.learningmate.domain.review.service;

import lombok.RequiredArgsConstructor;
import org.kc5.learningmate.api.v1.dto.request.review.ReviewCreateRequest;
import org.kc5.learningmate.common.exception.CommonException;
import org.kc5.learningmate.common.exception.ErrorCode;
import org.kc5.learningmate.domain.article.entity.Article;
import org.kc5.learningmate.domain.article.repository.ArticleRepository;
import org.kc5.learningmate.domain.member.entity.Member;
import org.kc5.learningmate.domain.member.repository.MemberRepository;
import org.kc5.learningmate.domain.review.repository.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;

    @Transactional
    public void createReview(Long articleId, ReviewCreateRequest request) {
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new CommonException(ErrorCode.MEMBER_NOT_FOUND));

        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new CommonException(ErrorCode.ARTICLE_NOT_FOUND));

        hasWrittenReview(member.getId(), article.getId());

        reviewRepository.save(ReviewCreateRequest.from(request, member, article));
    }

    // 사용자 리뷰 중복 확인 메서드
    @Transactional(readOnly = true)
    public void hasWrittenReview(Long memberId, Long articleId){
        boolean exists = reviewRepository.existsByMemberIdAndArticleId(memberId, articleId);
        if (exists) {
            throw new CommonException(ErrorCode.DUPLICATE_REVIEW);
        }
    }

}