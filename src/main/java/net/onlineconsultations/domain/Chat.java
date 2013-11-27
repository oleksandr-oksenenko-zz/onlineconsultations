package net.onlineconsultations.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "chat_id", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User consultantInChat;

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
}
