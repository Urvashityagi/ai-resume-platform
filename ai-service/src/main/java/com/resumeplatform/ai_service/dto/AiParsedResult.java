package com.resumeplatform.ai_service.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AiParsedResult {

    private String technicalSkills;

    private String softSkills;

    private String candidateSummary;
}