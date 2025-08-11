package org.kc5.learningmate.api.v1.controller;

import lombok.RequiredArgsConstructor;
import org.kc5.learningmate.api.v1.dto.response.KeywordDto;
import org.kc5.learningmate.api.v1.dto.response.TodaysKeywordDto;
import org.kc5.learningmate.common.ResultResponse;
import org.kc5.learningmate.domain.keyword.service.KeywordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
                             .body(new ResultResponse<>(HttpStatus.OK, todaysKeywordDtoList));
    }

    @GetMapping("/{keywordId}")
    public ResponseEntity<ResultResponse<KeywordDto>> findById(@PathVariable Long keywordId) {
        KeywordDto keywordDto = keywordService.findById(keywordId);
        return ResponseEntity.ok()
                             .body(new ResultResponse<>(HttpStatus.OK, keywordDto));
    }

}
