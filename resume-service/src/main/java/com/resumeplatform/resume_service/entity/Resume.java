package com.resumeplatform.resume_service.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "resumes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String originalFileName;

    private String storedFileName;

    private String filePath;

    private Long fileSize;

    private String uploadStatus;

    private LocalDateTime createdAt;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String extractedText;
}