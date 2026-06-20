package com.resumeplatform.resume_service.controller;

import com.resumeplatform.resume_service.dto.ResumeResponse;
import com.resumeplatform.resume_service.dto.ResumeTextResponse;
import com.resumeplatform.resume_service.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/resumes")
@RequiredArgsConstructor
public class ResumeController {

    private final ResumeService resumeService;

    @PostMapping("/upload")
    public ResumeResponse uploadResume(
            @RequestParam MultipartFile file,
            @RequestParam Long userId) {

        return resumeService.uploadResume(
                file,
                userId);
    }

    @GetMapping("/{resumeId}/text")
    public ResumeTextResponse getExtractedText(
            @PathVariable Long resumeId) {

        return resumeService
                .getExtractedText(resumeId);
    }
}