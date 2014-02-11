package net.onlineconsultations.controller.rest.model;

import net.onlineconsultations.domain.ChatMessage;
import org.joda.time.LocalDateTime;

public class ChatMessageInfo {
    private final String body;

    private final LocalDateTime timestamp;

    private final UserInfo author;

    public ChatMessageInfo(String body, LocalDateTime timestamp,
                           UserInfo author) {
        this.body = body;
        this.timestamp = timestamp;
        this.author = author;
    }

    public static ChatMessageInfo of(ChatMessage chatMessage) {
        return new ChatMessageInfo(
                chatMessage.getBody(),
                chatMessage.getTimestamp(),
                UserInfo.of(chatMessage.getAuthor()));
    }

    public String getBody() {
        return body;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public UserInfo getAuthor() {
        return author;
    }
}
