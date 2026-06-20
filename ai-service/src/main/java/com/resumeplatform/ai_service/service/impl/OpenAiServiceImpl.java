package com.resumeplatform.ai_service.service.impl;


import com.openai.client.OpenAIClient;
import com.openai.models.chat.completions.ChatCompletion;
import com.openai.models.chat.completions.ChatCompletionCreateParams;
import com.resumeplatform.ai_service.dto.AiParsedResult;
import com.resumeplatform.ai_service.service.OpenAiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OpenAiServiceImpl
        implements OpenAiService {

    private final OpenAIClient openAIClient;

    @Override
    public AiParsedResult analyzeResume(String resumeText) {

            String prompt = """
                    Analyze the resume and return ONLY in this format:
                    
                    TECHNICAL_SKILLS:
                    Java, Spring Boot, AWS
                    
                    SOFT_SKILLS:
                    Communication, Leadership
                    
                    SUMMARY:
                    Professional summary here
                    
                    Resume:
                    %s
                    """.formatted(resumeText);

        ChatCompletionCreateParams params =
                ChatCompletionCreateParams.builder()
                        .model("gpt-4.1-mini")
                        .addUserMessage(prompt)
                        .build();

        ChatCompletion completion =
                openAIClient.chat()
                        .completions()
                        .create(params);

        String response =
                completion.choices()
                        .get(0)
                        .message()
                        .content()
                        .orElse("");

        String technicalSkills =
                extractSection(
                        response,
                        "TECHNICAL_SKILLS:",
                        "SOFT_SKILLS:");

        String softSkills =
                extractSection(
                        response,
                        "SOFT_SKILLS:",
                        "SUMMARY:");

        String summary =
                extractSection(
                        response,
                        "SUMMARY:",
                        null);

        return AiParsedResult.builder()
                .technicalSkills(
                        technicalSkills)
                .softSkills(
                        softSkills)
                .candidateSummary(
                        summary)
                .build();
    }

    private String extractSection(
            String response,
            String startMarker,
            String endMarker) {

        int start = response.indexOf(startMarker);

        if (start == -1) {
            return "";
        }

        start += startMarker.length();

        int end = endMarker == null
                ? response.length()
                : response.indexOf(endMarker, start);

        if (end == -1) {
            end = response.length();
        }

        return response.substring(start, end)
                .trim();
    }
}