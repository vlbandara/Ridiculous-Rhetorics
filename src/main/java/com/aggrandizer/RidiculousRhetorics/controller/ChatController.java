package com.aggrandizer.RidiculousRhetorics.controller;

import org.springframework.ai.chat.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {
    private final ChatClient chatClient;

    public ChatController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/rhetorics")
    public String generateRhetorics(@RequestParam(value = "message", defaultValue = "tell me a Random Rhetorical Question") String message) {
        return chatClient.call(message);

    }
}
