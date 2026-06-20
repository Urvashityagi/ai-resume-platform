package com.resumeplatform.resume_service.dto;


import lombok.Data;

@Data
public class ResumeAnalysisRequest {

    private Long resumeId;

    private String resumeText;
}
