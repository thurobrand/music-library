package com.cobb.music_library.service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Service
public class OpenAIService {
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";
    private static final String API_KEY = "";
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    private final OkHttpClient client;
    private final ObjectMapper objectMapper;

    public OpenAIService() {
        this.client = new OkHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    public String generateResponse(String prompt) throws IOException {
        ChatCompletionRequest request = new ChatCompletionRequest(
            "gpt-3.5-turbo",
            Collections.singletonList(new Message("user", prompt)),
            1024
        );

        String requestBody = objectMapper.writeValueAsString(request);

        Request httpRequest = new Request.Builder()
            .url(API_URL)
            .header("Authorization", "Bearer " + API_KEY)
            .header("Content-Type", "application/json")
            .post(RequestBody.create(requestBody, JSON))
            .build();

        try (Response response = client.newCall(httpRequest).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            ChatCompletionResponse chatResponse = objectMapper.readValue(response.body().string(), ChatCompletionResponse.class);
            return chatResponse.getChoices().get(0).getMessage().getContent();
        }
    }

    private static class ChatCompletionRequest {
        private final String model;
        private final List<Message> messages;
        private final int max_tokens;

        public ChatCompletionRequest(String model, List<Message> messages, int max_tokens) {
            this.model = model;
            this.messages = messages;
            this.max_tokens = max_tokens;
        }

        // Getters
    }

    private static class Message {
        private final String role;
        private final String content;

        public Message(String role, String content) {
            this.role = role;
            this.content = content;
        }

        public String getContent() {
            return content;
        }
    }

    private static class ChatCompletionResponse {
        private List<Choice> choices;

        // Getter
        public List<Choice> getChoices() {
            return choices;
        }
    }

    private static class Choice {
        private Message message;

        // Getter
        public Message getMessage() {
            return message;
        }
    }
}
