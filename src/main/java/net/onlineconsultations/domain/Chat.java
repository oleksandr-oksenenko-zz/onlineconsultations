package net.onlineconsultations.domain;

public class Chat {
    private Long id;

    private User consultantInChat;

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
