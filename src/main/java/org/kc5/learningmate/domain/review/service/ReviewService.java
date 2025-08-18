package org.kc5.learningmate.domain.review.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kc5.learningmate.api.v1.dto.request.review.ReviewCreateRequest;
import org.kc5.learningmate.api.v1.dto.request.review.ReviewUpdateRequest;
import org.kc5.learningmate.api.v1.dto.response.MyReviewResponse;
import org.kc5.learningmate.api.v1.dto.response.ReviewResponse;
import org.kc5.learningmate.common.exception.CommonException;
import org.kc5.learningmate.common.exception.ErrorCode;
import org.kc5.learningmate.domain.article.entity.Article;
import org.kc5.learningmate.domain.article.repository.ArticleRepository;
import org.kc5.learningmate.domain.member.entity.Member;
import org.kc5.learningmate.domain.member.repository.MemberRepository;
import org.kc5.learningmate.domain.review.entity.Review;
import org.kc5.learningmate.domain.review.repository.LikeReviewRepository;
import org.kc5.learningmate.domain.review.repository.ReviewRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewService {

    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;
    private final LikeReviewRepository likeReviewRepository;

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
    public void hasWrittenReview(Long memberId, Long articleId) {
        boolean exists = reviewRepository.existsByMemberIdAndArticleId(memberId, articleId);
        if (exists) {
            throw new CommonException(ErrorCode.DUPLICATE_REVIEW);
        }
    }

    @Transactional(readOnly = true)
    public MyReviewResponse getReview(Long articleId, Long memberId) {
        articleRepository.findById(articleId)
                         .orElseThrow(() -> new CommonException(ErrorCode.ARTICLE_NOT_FOUND));

        memberRepository.findById(memberId)
                        .orElseThrow(() -> new CommonException(ErrorCode.MEMBER_NOT_FOUND));

        Review review = reviewRepository.findByArticleIdAndMemberId(articleId, memberId);
        return MyReviewResponse.from(review);
    }

    @Transactional
    public void updateReview(Long articleId, Long reviewId, ReviewUpdateRequest request) {
        memberRepository.findById(request.getMemberId())
                        .orElseThrow(() -> new CommonException(ErrorCode.MEMBER_NOT_FOUND));

        Review review = reviewRepository.findById(reviewId)
                                        .orElseThrow(() -> new CommonException(ErrorCode.REVIEW_NOT_FOUND));

        Article article = articleRepository.findById(articleId)
                                           .orElseThrow(() -> new CommonException(ErrorCode.ARTICLE_NOT_FOUND));

        // 소유/매핑 검증
        if (!review.getArticle()
                   .getId()
                   .equals(article.getId())) {
            throw new CommonException(ErrorCode.FORBIDDEN);
        }

        review.update(request.getContent1(), request.getContent2(), request.getContent3());
    }

    @Transactional
    public void deleteReview(Long articleId, Long reviewId) {
        Article article = articleRepository.findById(articleId)
                                           .orElseThrow(() ->
                                                   new CommonException(ErrorCode.ARTICLE_NOT_FOUND));

        Review review = reviewRepository.findById(reviewId)
                                        .orElseThrow(() ->
                                                new CommonException(ErrorCode.REVIEW_NOT_FOUND));

        if (!review.getArticle()
                   .getId()
                   .equals(article.getId())) {
            throw new CommonException(ErrorCode.FORBIDDEN);
        }
        reviewRepository.deleteById(reviewId);
    }

    @Transactional
    public void likeReview(Long reviewId, Long memberId) {

        int affected = likeReviewRepository.insertIgnore(memberId, reviewId);
        if (affected == 0) {
            log.info("이미 좋아요 상태이므로 요청 무시 - reviewId={}, memberId={}", reviewId, memberId);
        }

    }

    @Transactional
    public void unlikeReview(Long reviewId, Long memberId) {
        int deleted = likeReviewRepository.deleteDirect(reviewId, memberId);

        if (deleted == 0) log.info("이미 좋아요 취소 상태이므로 요청 무시 - reviewId={}, memberId={}", reviewId, memberId);
    }

    @Transactional
    public List<ReviewResponse> getReviewsByArticleId(Long articleId, Pageable pageable) {
        Page<Review> reviews = reviewRepository.findByArticleId(articleId, pageable);

        return reviews.map(ReviewResponse::from)
                      .getContent();
    }

}