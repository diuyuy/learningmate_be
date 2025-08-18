package org.kc5.learningmate.domain.review.repository;

import org.kc5.learningmate.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    boolean existsByMemberIdAndArticleId(Long memberId, Long articleId);
    Optional<Review> findByArticleIdAndMemberId(Long articleId, Long memberId);
}
