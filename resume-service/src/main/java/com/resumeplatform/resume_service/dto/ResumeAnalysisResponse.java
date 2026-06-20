package com.resumeplatform.resume_service.dto;


import lombok.Data;

@Data
public class ResumeAnalysisResponse {

    private Long resumeId;

    private String aiAnalysis;
}
