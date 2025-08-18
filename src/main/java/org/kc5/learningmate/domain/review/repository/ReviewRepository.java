package org.kc5.learningmate.domain.review.repository;

import org.kc5.learningmate.api.v1.dto.response.ReviewResponse;
import org.kc5.learningmate.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    boolean existsByMemberIdAndArticleId(Long memberId, Long articleId);

    Optional<Review> findByArticleIdAndMemberId(Long articleId, Long memberId);

    Review findByArticleIdAndMemberId(Long articleId, Long memberId);

    Page<Review> findByArticleId(Long articleId, Pageable pageable);
    
    @Query(value = """
            select new org.kc5.learningmate.api.v1.dto.response.ReviewResponse(
                            r.id, r.updatedAt, r.content1, r.member.id
                        )
                        from Review r inner join r.article a
                        where a.keyword.id = :keywordId
            """)
    Page<ReviewResponse> findByKeywordId(@Param("keywordId") Long keywordId, Pageable pageable);

}
