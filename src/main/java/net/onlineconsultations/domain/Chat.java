package net.onlineconsultations.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "chat")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "session_id")
    @Size(min = 32, max = 32)
    private String sessionId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull
    private User consultantInChat;

    @Enumerated(EnumType.STRING)
    @Column(name = "chat_status")
    @NotNull
    private ChatStatus status = ChatStatus.ACTIVE;

    @Column(name = "is_consultant_in_chat", columnDefinition = "tinyint")
    private boolean isConsultantInChat = false;

    @Column(name = "is_anonym_in_chat", columnDefinition = "tinyint")
    private boolean isAnonymInChat = false;

    public Chat() { }

    public Chat(User consultantInChat) {
        this.consultantInChat = consultantInChat;
    }

    public Chat(String sessionId, User consultantInChat, boolean isAnonymInChat) {
        this.sessionId = sessionId;
        this.consultantInChat = consultantInChat;
        this.isAnonymInChat = isAnonymInChat;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getConsultantInChat() {
        return consultantInChat;
    }

    public void setConsultantInChat(User consultantInChat) {
        this.consultantInChat = consultantInChat;
    }

    public ChatStatus getStatus() {
        return status;
    }

    public void setStatus(ChatStatus chatStatus) {
        this.status = chatStatus;
    }

    public boolean isConsultantInChat() {
        return isConsultantInChat;
    }

    public void setConsultantInChat(boolean isConsultantInChat) {
        this.isConsultantInChat = isConsultantInChat;
    }

    public boolean isAnonymInChat() {
        return isAnonymInChat;
    }

    public void setAnonymInChat(boolean isAnonymInChat) {
        this.isAnonymInChat = isAnonymInChat;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
