package org.kc5.learningmate.domain.keyword.repository;

import org.kc5.learningmate.domain.keyword.entity.TodaysKeyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodayKeywordRepository extends JpaRepository<TodaysKeyword, Long> {
}
