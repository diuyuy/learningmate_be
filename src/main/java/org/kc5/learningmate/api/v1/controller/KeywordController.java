package org.kc5.learningmate.api.v1.controller;

import lombok.RequiredArgsConstructor;
import org.kc5.learningmate.api.v1.dto.response.TodaysKeywordDto;
import org.kc5.learningmate.common.ResultResponse;
import org.kc5.learningmate.domain.keyword.service.KeywordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RequestMapping("/api/v1/keywords")
@RequiredArgsConstructor
@RestController
public class KeywordController {
    private final KeywordService keywordService;

    @GetMapping()
    public ResponseEntity<ResultResponse<List<TodaysKeywordDto>>> findAllWithKeywords(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        List<TodaysKeywordDto> todaysKeywordDtoList = keywordService.findByPeriodWithKeywords(startDate, endDate);

        return ResponseEntity.ok()
                             .body(new ResultResponse<List<TodaysKeywordDto>>(HttpStatus.OK, todaysKeywordDtoList));
    }

}
