package net.onlineconsultations.controller.rest.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import net.onlineconsultations.domain.ChatMessage;

public class ChatMessageInfo {
    private final String body;

    @JsonCreator
    public ChatMessageInfo(@JsonProperty("body") String body) {
        this.body = body;
    }

    public static ChatMessageInfo of(ChatMessage chatMessage) {
        return new ChatMessageInfo(
                chatMessage.getBody()
        );
    }

    public String getBody() {
        return body;
    }
}
