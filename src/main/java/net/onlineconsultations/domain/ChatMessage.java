package net.onlineconsultations.domain;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "chat_message")
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "body")
    @Lob
    private String body;

    @Column(name = "timestamp")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "chat_id")
    @NotNull
    private Chat chat;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Consultant author;

    public ChatMessage() { }

    public ChatMessage(String body, LocalDateTime sendTimestamp,
                       Chat chat, Consultant author) {
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

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime sendTimestamp) {
        this.timestamp = sendTimestamp;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public Consultant getAuthor() {
        return author;
    }

    public void setAuthor(Consultant author) {
        this.author = author;
    }
}
