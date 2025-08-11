package org.kc5.learningmate.domain.keyword.service;

import lombok.RequiredArgsConstructor;
import org.kc5.learningmate.api.v1.dto.response.TodaysKeywordDto;
import org.kc5.learningmate.domain.keyword.entity.TodaysKeyword;
import org.kc5.learningmate.domain.keyword.repository.KeywordRepository;
import org.kc5.learningmate.domain.keyword.repository.TodayKeywordRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KeywordService {
    private final KeywordRepository keywordRepository;
    private final TodayKeywordRepository todayKeywordRepository;

    @Transactional(readOnly = true)
    public List<TodaysKeywordDto> findByPeriodWithKeywords(LocalDate startDate, LocalDate endDate) {
        List<TodaysKeyword> todaysKeywords = todayKeywordRepository.findByPeriodWithKeyword(startDate, endDate);

        return todaysKeywords.stream()
                             .map(TodaysKeywordDto::fromEntityWithKeyword)
                             .toList();
    }
}
