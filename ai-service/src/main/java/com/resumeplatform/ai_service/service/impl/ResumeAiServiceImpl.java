package com.resumeplatform.ai_service.service.impl;

import com.resumeplatform.ai_service.dto.AiParsedResult;
import com.resumeplatform.ai_service.dto.ResumeAnalysisRequest;
import com.resumeplatform.ai_service.dto.ResumeAnalysisResponse;
import com.resumeplatform.ai_service.entity.ResumeAnalysis;
import com.resumeplatform.ai_service.repository.ResumeAnalysisRepository;
import com.resumeplatform.ai_service.service.OpenAiService;
import com.resumeplatform.ai_service.service.ResumeAiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ResumeAiServiceImpl
        implements ResumeAiService {

    private final ResumeAnalysisRepository repository;
    private final OpenAiService openAiService;

    @Override
    public ResumeAnalysisResponse analyzeResume(
            ResumeAnalysisRequest request) {


        AiParsedResult result =
                openAiService.analyzeResume(
                        request.getResumeText());

        ResumeAnalysis analysis =
                ResumeAnalysis.builder()
                        .resumeId(request.getResumeId())
                        .technicalSkills(
                                result.getTechnicalSkills())
                        .softSkills(
                                result.getSoftSkills())
                        .candidateSummary(
                                result.getCandidateSummary())
                        .analyzedAt(LocalDateTime.now())
                        .build();

        repository.save(analysis);


        return ResumeAnalysisResponse.builder()
                .resumeId(request.getResumeId())
                .technicalSkills(
                        result.getTechnicalSkills())
                .softSkills(
                        result.getSoftSkills())
                .candidateSummary(
                        result.getCandidateSummary())
                .build();
    }
}