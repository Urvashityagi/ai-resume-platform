package com.resumeplatform.resume_service.service.impl;

import com.resumeplatform.resume_service.dto.ResumeResponse;
import com.resumeplatform.resume_service.entity.Resume;
import com.resumeplatform.resume_service.repository.ResumeRepository;
import com.resumeplatform.resume_service.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepository resumeRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public ResumeResponse uploadResume(
            MultipartFile file,
            Long userId) {

        try {

            String originalFileName =
                    file.getOriginalFilename();

            if (originalFileName == null ||
                    !originalFileName.endsWith(".pdf")) {

                throw new RuntimeException(
                        "Only PDF files are allowed");
            }

            File folder = new File(uploadDir);

            if (!folder.exists()) {
                folder.mkdirs();
            }

            String storedFileName =
                    UUID.randomUUID() + "_" + originalFileName;

            String filePath =
                    uploadDir + File.separator + storedFileName;

            file.transferTo(new File(filePath));

            Resume resume = Resume.builder()
                    .userId(userId)
                    .originalFileName(originalFileName)
                    .storedFileName(storedFileName)
                    .filePath(filePath)
                    .fileSize(file.getSize())
                    .uploadStatus("UPLOADED")
                    .createdAt(LocalDateTime.now())
                    .build();

            Resume saved =
                    resumeRepository.save(resume);

            return ResumeResponse.builder()
                    .resumeId(saved.getId())
                    .userId(saved.getUserId())
                    .fileName(saved.getOriginalFileName())
                    .status(saved.getUploadStatus())
                    .build();

        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
}
