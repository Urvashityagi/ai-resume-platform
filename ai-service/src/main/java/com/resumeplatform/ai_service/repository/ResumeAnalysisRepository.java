package com.resumeplatform.ai_service.repository;


import com.resumeplatform.ai_service.entity.ResumeAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeAnalysisRepository
        extends JpaRepository<ResumeAnalysis, Long> {
}
