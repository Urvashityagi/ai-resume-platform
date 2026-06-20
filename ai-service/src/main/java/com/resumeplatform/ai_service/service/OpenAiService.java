package com.resumeplatform.ai_service.service;


import com.resumeplatform.ai_service.dto.AiParsedResult;

public interface OpenAiService {

    AiParsedResult analyzeResume(String resumeText);
}
