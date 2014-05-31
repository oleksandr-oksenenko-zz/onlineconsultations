package net.onlineconsultations.web.chat.rest.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import net.onlineconsultations.domain.Chat;

public final class ChatInfo {
    @JsonProperty("chatId")
    private final Long chatId;

    @JsonProperty("isAnonymInChat")
    private final boolean anonymInChat;

    @JsonProperty("isConsultantInChat")
    private final boolean consultantInChat;

    public static ChatInfo of(Chat chat) {
        return new ChatInfo(
                chat.getId(),
                chat.isAnonymInChat(),
                chat.isConsultantInChat()
        );
    }

    public ChatInfo() {
        this.chatId = -1L;
        this.anonymInChat = false;
        this.consultantInChat = false;
    }

    @JsonCreator
    public ChatInfo(@JsonProperty("chatId") Long chatId,
                    @JsonProperty("isAnonymInChat") boolean anonymInChat,
                    @JsonProperty("isConsultantInChat") boolean consultantInChat) {
        this.chatId = chatId;
        this.anonymInChat = anonymInChat;
        this.consultantInChat = consultantInChat;
    }


    public Long getChatId() {
        return chatId;
    }

    public boolean isAnonymInChat() {
        return anonymInChat;
    }

    public boolean isConsultantInChat() {
        return consultantInChat;
    }
}
