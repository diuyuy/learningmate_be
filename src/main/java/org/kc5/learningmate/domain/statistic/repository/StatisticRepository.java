package org.kc5.learningmate.domain.statistic.repository;

import org.kc5.learningmate.domain.statistic.entity.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticRepository extends JpaRepository<Statistic, Long> {

}
