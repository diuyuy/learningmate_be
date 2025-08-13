package org.kc5.learningmate.domain.quiz.repository;

import org.kc5.learningmate.domain.quiz.entity.MemberQuiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberQuizRepository extends JpaRepository<MemberQuiz, Long> {

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("""
        UPDATE MemberQuiz mq
           SET mq.memberAnswer = :memberAnswer
           , mq.updatedAt = CURRENT_TIMESTAMP
         WHERE mq.quiz.id = :quizId
           AND mq.member.id = :memberId
    """)
    int updateAnswer(Long quizId, Long memberId, String memberAnswer);
}
