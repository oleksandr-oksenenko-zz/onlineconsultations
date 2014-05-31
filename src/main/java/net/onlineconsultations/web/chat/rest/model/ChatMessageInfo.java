package net.onlineconsultations.web.chat.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.onlineconsultations.domain.ChatMessage;
import net.onlineconsultations.domain.User;

import javax.validation.constraints.NotNull;

public class ChatMessageInfo {
    private Long id;

    @NotNull
    private String body;

    private String author;

    public ChatMessageInfo() {

    }

    public ChatMessageInfo(Long id, String body, String author) {
        this.id = id;
        this.body = body;
        this.author = author;
    }

    public static ChatMessageInfo of(ChatMessage chatMessage) {
        User author = chatMessage.getAuthor();
        String authorStr = null;
        if (author != null) {
            authorStr = chatMessage.getAuthor().getFirstName() + " " +
                    chatMessage.getAuthor().getLastName();
        } else {
            authorStr = "anonym";
        }

        return new ChatMessageInfo(
                chatMessage.getId(),
                chatMessage.getBody(),
                authorStr
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

    public String getAuthor() {
        return author;
    }

    @JsonProperty("author")
    public void setAuthor(String author) {
        this.author = author;
    }
}
