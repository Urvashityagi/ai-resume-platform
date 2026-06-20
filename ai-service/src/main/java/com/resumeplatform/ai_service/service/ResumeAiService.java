package com.resumeplatform.ai_service.service;

import com.resumeplatform.ai_service.dto.ResumeAnalysisRequest;
import com.resumeplatform.ai_service.dto.ResumeAnalysisResponse;

public interface ResumeAiService {

    ResumeAnalysisResponse analyzeResume(
            ResumeAnalysisRequest request);

    ResumeAnalysisResponse getAnalysis(
            Long resumeId);
}