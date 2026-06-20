package com.resumeplatform.resume_service.service;

import com.resumeplatform.resume_service.dto.ResumeResponse;
import org.springframework.web.multipart.MultipartFile;

public interface ResumeService {

    ResumeResponse uploadResume(
            MultipartFile file,
            Long userId);
}
