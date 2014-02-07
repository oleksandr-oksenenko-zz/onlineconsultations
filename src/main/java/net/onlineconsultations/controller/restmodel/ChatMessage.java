package net.onlineconsultations.controller.restmodel;

import org.joda.time.LocalDateTime;

public class ChatMessage {
    private Long id;

    private String body;

    private LocalDateTime timestamp;

    private User author;

    public ChatMessage(Long id, String body, LocalDateTime timestamp,
                       User author) {
        this.id = id;
        this.body = body;
        this.timestamp = timestamp;
        this.author = author;
    }

    public ChatMessage(net.onlineconsultations.domain.ChatMessage chatMessage) {
        this.id = chatMessage.getId();
        this.body = chatMessage.getBody();
        this.timestamp = chatMessage.getTimestamp();
        if (chatMessage.getAuthor() != null) {
            this.author = new User(chatMessage.getAuthor());
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
