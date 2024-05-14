package com.aggrandizer.RidiculousRhetorics.controller;

import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.parser.MapOutputParser;
import org.springframework.ai.parser.OutputParser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
public class ChatController {
    private final ChatClient chatClient;

    public ChatController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/rhetorics/{type}")
    public Map<String,Object> getRhetorics(@PathVariable String type){
        String promptMessage = """
                generate a rhetoric question and answer about {type}. Include in  {format}
                """;
        MapOutputParser outputParser = new MapOutputParser();
        String format = outputParser.getFormat();
        PromptTemplate promptTemplate = new PromptTemplate(promptMessage, Map.of("type", type, "format", format));
        Prompt prompt = promptTemplate.create();
        Generation generation = chatClient.call(prompt).getResult();
        return outputParser.parse(generation.getOutput().getContent());
    }

}
