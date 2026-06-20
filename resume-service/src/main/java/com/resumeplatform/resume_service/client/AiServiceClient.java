package com.resumeplatform.resume_service.client;

import com.resumeplatform.resume_service.dto.ResumeAnalysisRequest;
import com.resumeplatform.resume_service.dto.ResumeAnalysisResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "ai-service",
        url = "${ai.service.url}"
)
public interface AiServiceClient {

    @PostMapping("/api/ai/analyze")
    ResumeAnalysisResponse analyzeResume(
            @RequestBody ResumeAnalysisRequest request);
}
