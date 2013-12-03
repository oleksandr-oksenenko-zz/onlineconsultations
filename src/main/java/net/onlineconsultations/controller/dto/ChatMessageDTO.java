package net.onlineconsultations.controller.dto;

import java.util.Calendar;

import net.onlineconsultations.domain.ChatMessage;

public class ChatMessageDTO {
    private Long id;

    private String body;

    private Calendar timestamp;

    private UserDTO author;

    public ChatMessageDTO(Long id, String body, Calendar timestamp,
            UserDTO author) {
        this.id = id;
        this.body = body;
        this.timestamp = timestamp;
        this.author = author;
    }

    public ChatMessageDTO(ChatMessage chatMessage) {
        this.id = chatMessage.getId();
        this.body = chatMessage.getBody();
        this.timestamp = chatMessage.getTimestamp();
        if (chatMessage.getAuthor() != null) {
            this.author = new UserDTO(chatMessage.getAuthor());
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

    public Calendar getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Calendar timestamp) {
        this.timestamp = timestamp;
    }

    public UserDTO getAuthor() {
        return author;
    }

    public void setAuthor(UserDTO author) {
        this.author = author;
    }
}
