package org.kc5.learningmate.domain.study.repository;

import org.kc5.learningmate.domain.study.entity.Study;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyRepository extends JpaRepository<Study, Long> {

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value =
            """ 
            INSERT INTO study
            (member_id, keyword_id, study_stats, created_at, updated_at) 
            VALUES 
            (:memberId, :keywordId, :flag, NOW(6), NOW(6)) 
            ON DUPLICATE KEY UPDATE 
            study_stats = study.study_stats | VALUES(study_stats), updated_at = NOW(6) 
            """, nativeQuery = true)
    void upsertFlag(@Param("memberId") Long memberId, @Param("keywordId") Long keywordId, @Param("flag") int flag);
}
