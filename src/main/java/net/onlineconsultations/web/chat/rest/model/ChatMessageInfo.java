package net.onlineconsultations.web.chat.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.onlineconsultations.domain.ChatMessage;

import javax.validation.constraints.NotNull;

public class ChatMessageInfo {
    private Long id;

    @NotNull
    private String body;

    public ChatMessageInfo() {

    }

    public ChatMessageInfo(Long id, String body) {
        this.id = id;
        this.body = body;
    }

    public static ChatMessageInfo of(ChatMessage chatMessage) {
        return new ChatMessageInfo(
                chatMessage.getId(),
                chatMessage.getBody()
        );
    }

    public Long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    @JsonProperty("body")
    public void setBody(String body) {
        this.body = body;
    }
}
