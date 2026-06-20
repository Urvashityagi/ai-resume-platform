package com.resumeplatform.resume_service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResumeTextResponse {

    private Long resumeId;

    private String extractedText;
}
