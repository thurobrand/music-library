package com.cobb.music_library.agents;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class ScoreParseMessageAgent {
    private static final String API_URL = "https://api.anthropic.com/v1/messages";
    private static final String API_KEY = "YOUR_API_KEY";
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    private final OkHttpClient client;
    private final ObjectMapper objectMapper;

    
    private static class MessageResponse {
        @JsonProperty("content")
        public String content;
    }
    
    private static class MessageRequest {
        @JsonProperty("model")
        private final String model = "claude-3-sonnet-20240229";

        @JsonProperty("max_tokens")
        private final int maxTokens = 1024;

        @JsonProperty("messages")
        private final List<Message> messages;

        public MessageRequest(String prompt) {
            this.messages = Collections.singletonList(new Message("user", prompt));
        }

        private static class Message {
            @JsonProperty("role")
            private final String role;

            @JsonProperty("content")
            private final String content;

            public Message(String role, String content) {
                this.role = role;
                this.content = content;
            }
        }
    }
    public ScoreParseMessageAgent() {
        this.client = new OkHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    public String parseScorePDF(File pdfFile) throws IOException {
        String pdfText = extractTextFromPDF(pdfFile);
        return parseScore(pdfText);
    }

    private String extractTextFromPDF(File pdfFile) throws IOException {
        try (PDDocument document = PDDocument.load(pdfFile)) {
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(document);
        }
    }

    private String parseScore(String scoreText) throws IOException {
        String prompt = "Parse the following score text and extract relevant information: " + scoreText;

        String requestBody = objectMapper.writeValueAsString(new MessageRequest(prompt));

        Request request = new Request.Builder()
            .url(API_URL)
            .header("x-api-key", API_KEY)
            .header("content-type", "application/json")
            .post(RequestBody.create(requestBody, JSON))
            .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            MessageResponse messageResponse = objectMapper.readValue(response.body().string(), MessageResponse.class);
            return messageResponse.content;
        }
    }

    // ... (MessageRequest and MessageResponse classes remain the same)
}