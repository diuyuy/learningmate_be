package org.kc5.learningmate.domain.study.service;

import lombok.RequiredArgsConstructor;
import org.kc5.learningmate.api.v1.dto.response.VideoResponse;
import org.kc5.learningmate.common.exception.CommonException;
import org.kc5.learningmate.common.exception.ErrorCode;
import org.kc5.learningmate.domain.keyword.service.KeywordService;
import org.kc5.learningmate.domain.study.repository.VideoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VideoService {
    private final VideoRepository videoRepository;
    private final KeywordService keywordService;

    @Transactional(readOnly = true)
    public VideoResponse findByKeywordId(Long keywordId) {
        keywordService.validateKeywordExists(keywordId);

        return videoRepository.findByKeywordId(keywordId)
                              .map(VideoResponse::fromEntity)
                              .orElseThrow(() -> new CommonException(ErrorCode.VIDEO_BY_KEYWORD_ID_NOT_FOUND));
    }
}
