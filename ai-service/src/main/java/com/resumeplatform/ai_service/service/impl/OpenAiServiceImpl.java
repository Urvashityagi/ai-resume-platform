package com.resumeplatform.ai_service.service.impl;


import com.openai.client.OpenAIClient;
import com.openai.models.chat.completions.ChatCompletion;
import com.openai.models.chat.completions.ChatCompletionCreateParams;
import com.resumeplatform.ai_service.service.OpenAiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OpenAiServiceImpl
        implements OpenAiService {

    private final OpenAIClient openAIClient;

    @Override
    public String analyzeResume(String resumeText) {

        String prompt = """
                Analyze this resume.

                Return:
                1. Technical Skills
                2. Soft Skills
                3. Professional Summary

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

        return completion.choices()
                .get(0)
                .message()
                .content()
                .orElse("");
    }
}