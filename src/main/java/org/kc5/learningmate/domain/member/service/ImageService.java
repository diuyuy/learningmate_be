package org.kc5.learningmate.domain.member.service;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.kc5.learningmate.common.exception.CommonException;
import org.kc5.learningmate.common.exception.ErrorCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class ImageService {
    @Value("${file.upload-dir}")
    private String uploadDir;

    @Value("${image-prefix}")
    private String imagePrefix;

    @Value("${file.allowed-extensions}")
    private List<String> allowedExtensions;

    private Path uploadPath;

    @PostConstruct
    public void init() {
        this.uploadPath = Paths.get(uploadDir);
        try {
            Files.createDirectories(this.uploadPath);
        } catch (IOException e) {
            log.error("디렉토리를 생성할 수 없습니다: {}", e.getMessage());
            throw new IllegalStateException("필수 업로드 디렉토리를 생성할 수 없습니다: " + uploadDir, e);
        }
    }

    public String saveImage(InputStream inputStream, String originalFilename, String oldImgUrl) {
        log.debug("saveImage 메서드 호출!!!");
        String filename = Paths.get(originalFilename)
                               .getFileName()
                               .toString();

        String extension = extractExtension(filename);
        validateFileExtension(extension);

        String newFilename = UUID.randomUUID() + "." + extension;

        Path path = uploadPath.resolve(newFilename);

        log.debug("저장할 이미지의 파일 경로: {}", path);

        try {
            Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);

            if (oldImgUrl != null && !oldImgUrl.isEmpty()) {
                deleteOldImage(oldImgUrl);
            }

            return imagePrefix + newFilename;
        } catch (IOException e) {
            throw new CommonException(ErrorCode.SAVE_IMAGE_FAIL);
        }
    }

    private void deleteOldImage(String imgUrl) {
        log.debug("deleteOldImage 호출!!!");
        if (!imgUrl.startsWith(imagePrefix)) {
            return;
        }

        String fileNameWithPotentialPath = imgUrl.substring(imagePrefix.length());
        String filename = Paths.get(fileNameWithPotentialPath)
                               .getFileName()
                               .toString();

        log.debug("파일 이름: {}", filename);
        Path path = this.uploadPath.resolve(filename);

        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            log.error("프로필 이미지 제거 에러: {}", e.getMessage());
        }

    }

    private String extractExtension(String filename) {
        try {
            return filename.substring(filename.lastIndexOf('.') + 1)
                           .toLowerCase();
        } catch (StringIndexOutOfBoundsException e) {
            throw new CommonException(ErrorCode.INVALID_FILE_EXTENSION);
        }
    }

    private void validateFileExtension(String extension) {
        if (!allowedExtensions.contains(extension)) {
            throw new CommonException(ErrorCode.INVALID_FILE_EXTENSION);
        }
    }
}
