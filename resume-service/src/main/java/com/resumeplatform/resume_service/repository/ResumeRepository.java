package com.resumeplatform.resume_service.repository;

import com.resumeplatform.resume_service.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
}