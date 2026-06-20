package com.resumeplatform.ai_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "resume_analysis")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResumeAnalysis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long resumeId;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String technicalSkills;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String softSkills;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String candidateSummary;

    private LocalDateTime analyzedAt;
}
