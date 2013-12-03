package net.onlineconsultations.domain;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "chat_message")
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "chat_message_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "body")
    @Lob
    private String body;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timestamp")
    private Calendar timestamp;

    @ManyToOne
    @JoinColumn(name = "chat_id", nullable = false)
    private Chat chat;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = true)
    private User author;

    public ChatMessage() {}

    public ChatMessage(String body, Calendar sendTimestamp, Chat chat,
            User author) {
        this.body = body;
        this.timestamp = sendTimestamp;
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

    public Calendar getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Calendar sendTimestamp) {
        this.timestamp = sendTimestamp;
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
