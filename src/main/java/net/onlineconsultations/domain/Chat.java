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

@Entity
@Table(name = "chat")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User consultantInChat;

    @Enumerated(EnumType.STRING)
    @Column(name = "chat_status", nullable = false)
    private ChatStatus status = ChatStatus.ACTIVE;

    @Column(name = "is_consultant_in_chat", columnDefinition = "tinyint")
    private boolean isConsultantInChat = false;

    @Column(name = "is_anonym_in_chat", columnDefinition = "tinyint")
    private boolean isAnonymInChat = false;

    public Chat() {
    }

    public Chat(User consultantInChat) {
        this.consultantInChat = consultantInChat;
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
}
