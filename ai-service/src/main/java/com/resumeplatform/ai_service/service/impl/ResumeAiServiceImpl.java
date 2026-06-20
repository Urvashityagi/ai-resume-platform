package com.resumeplatform.ai_service.service.impl;

import com.resumeplatform.ai_service.dto.ResumeAnalysisRequest;
import com.resumeplatform.ai_service.dto.ResumeAnalysisResponse;
import com.resumeplatform.ai_service.entity.ResumeAnalysis;
import com.resumeplatform.ai_service.repository.ResumeAnalysisRepository;
import com.resumeplatform.ai_service.service.ResumeAiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ResumeAiServiceImpl
        implements ResumeAiService {

    private final ResumeAnalysisRepository repository;

    @Override
    public ResumeAnalysisResponse analyzeResume(
            ResumeAnalysisRequest request) {

        String skills =
                "Java, Spring Boot, MySQL";

        String summary =
                "Experienced Java Backend Developer";

        ResumeAnalysis analysis =
                ResumeAnalysis.builder()
                        .resumeId(request.getResumeId())
                        .extractedSkills(skills)
                        .candidateSummary(summary)
                        .analyzedAt(LocalDateTime.now())
                        .build();

        repository.save(analysis);

        return ResumeAnalysisResponse.builder()
                .resumeId(request.getResumeId())
                .extractedSkills(skills)
                .candidateSummary(summary)
                .build();
    }
}