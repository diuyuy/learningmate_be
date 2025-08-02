package org.kc5.learningmate.domain.quiz.repository;

import org.kc5.learningmate.domain.quiz.entity.UserQuiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserQuizRepository extends JpaRepository<UserQuiz, Long> {
}
