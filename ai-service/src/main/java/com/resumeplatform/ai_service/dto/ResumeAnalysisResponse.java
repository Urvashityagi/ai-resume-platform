package com.resumeplatform.ai_service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResumeAnalysisResponse {

    private Long resumeId;

    private String extractedSkills;

    private String candidateSummary;
    private String aiAnalysis;
}