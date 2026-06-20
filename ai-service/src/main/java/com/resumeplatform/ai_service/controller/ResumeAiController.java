package com.resumeplatform.ai_service.controller;

import com.resumeplatform.ai_service.dto.ResumeAnalysisRequest;
import com.resumeplatform.ai_service.dto.ResumeAnalysisResponse;
import com.resumeplatform.ai_service.service.ResumeAiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class ResumeAiController {

    private final ResumeAiService resumeAiService;

    @PostMapping("/analyze")
    public ResumeAnalysisResponse analyzeResume(
            @RequestBody ResumeAnalysisRequest request) {

        return resumeAiService.analyzeResume(request);
    }

    @GetMapping("/resume/{resumeId}")
    public ResumeAnalysisResponse getAnalysis(
            @PathVariable Long resumeId) {

        return resumeAiService
                .getAnalysis(resumeId);
    }
}
