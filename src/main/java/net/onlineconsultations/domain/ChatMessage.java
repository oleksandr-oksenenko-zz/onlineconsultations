package net.onlineconsultations.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "chat_id")
    @NotNull
    private Chat chat;

    @Column(name = "written_by_anonym", columnDefinition = "tinyint")
    private boolean writtenByAnonym = false;

    public ChatMessage() { }

    public ChatMessage(String body, LocalDateTime sendTimestamp,
                       Chat chat, boolean writtenByAnonym) {
        this.body = body;
        this.timestamp = sendTimestamp;
        this.chat = chat;
        this.writtenByAnonym = writtenByAnonym;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ChatMessage)) {
            return false;
        }
        ChatMessage rhs = (ChatMessage) obj;
        return new EqualsBuilder()
                .append(id, rhs.id)
                .append(body, rhs.body)
                .append(timestamp, rhs.timestamp)
                .append(chat.getId(), rhs.getChat().getId())
                .append(writtenByAnonym, rhs.writtenByAnonym)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(id)
                .append(body)
                .append(timestamp)
                .append(chat.getId())
                .append(writtenByAnonym)
                .toHashCode();
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

    public boolean isWrittenByAnonym() {
        return writtenByAnonym;
    }

    public void setWrittenByAnonym(boolean writtenByAnonym) {
        this.writtenByAnonym = writtenByAnonym;
    }
}
