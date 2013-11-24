package net.onlineconsultations.domain;

import java.util.Calendar;

public class ChatMessage {
    private Long id;

    private String body;

    private Calendar sendTimestamp;

    private Chat chat;

    private User author;

    public ChatMessage(String body, Calendar sendTimestamp, Chat chat,
            User author) {
        this.body = body;
        this.sendTimestamp = sendTimestamp;
        this.chat = chat;
        this.author = author;
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

    public Calendar getSendTimestamp() {
        return sendTimestamp;
    }

    public void setSendTimestamp(Calendar sendTimestamp) {
        this.sendTimestamp = sendTimestamp;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
