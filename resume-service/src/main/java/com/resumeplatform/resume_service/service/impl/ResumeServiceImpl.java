package com.resumeplatform.resume_service.service.impl;

import com.resumeplatform.resume_service.client.AiServiceClient;
import com.resumeplatform.resume_service.dto.ResumeAnalysisRequest;
import com.resumeplatform.resume_service.dto.ResumeAnalysisResponse;
import com.resumeplatform.resume_service.dto.ResumeResponse;
import com.resumeplatform.resume_service.dto.ResumeTextResponse;
import com.resumeplatform.resume_service.entity.Resume;
import com.resumeplatform.resume_service.repository.ResumeRepository;
import com.resumeplatform.resume_service.service.ResumeService;
import com.resumeplatform.resume_service.service.pdf.PdfExtractionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.util.UUID;


@Service
@Slf4j
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepository resumeRepository;
    private final PdfExtractionService pdfExtractionService;

    private final AiServiceClient aiServiceClient;
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
            String extractedText =
                    pdfExtractionService.extractText(filePath);



            Resume resume = Resume.builder()
                    .userId(userId)
                    .originalFileName(originalFileName)
                    .storedFileName(storedFileName)
                    .filePath(filePath)
                    .fileSize(file.getSize())
                    .uploadStatus("UPLOADED")
                    .extractedText(extractedText)
                    .createdAt(LocalDateTime.now())
                    .build();

            Resume saved = resumeRepository.save(resume);

            ResumeAnalysisRequest request =
                    new ResumeAnalysisRequest();

            request.setResumeId(saved.getId());
            request.setResumeText(extractedText);

            try {
                aiServiceClient.analyzeResume(request);
            } catch (Exception e) {
                log.error("Failed to analyze resume: {}", e.getMessage());
            }

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

    @Override
    public ResumeTextResponse getExtractedText(Long resumeId) {

        Resume resume = resumeRepository
                .findById(resumeId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Resume not found"));

        return ResumeTextResponse.builder()
                .resumeId(resume.getId())
                .extractedText(
                        resume.getExtractedText())
                .build();
    }
}
