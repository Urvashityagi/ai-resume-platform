package com.resumeplatform.resume_service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResumeResponse {

    private Long resumeId;

    private Long userId;

    private String fileName;

    private String status;
}
