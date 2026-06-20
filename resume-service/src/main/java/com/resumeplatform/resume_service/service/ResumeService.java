package com.resumeplatform.resume_service.service;

import com.resumeplatform.resume_service.dto.ResumeResponse;
import com.resumeplatform.resume_service.dto.ResumeTextResponse;
import org.springframework.web.multipart.MultipartFile;

public interface ResumeService {

    ResumeResponse uploadResume(
            MultipartFile file,
            Long userId);

    ResumeTextResponse getExtractedText(Long resumeId);
}
