package net.onlineconsultations.web.chat.rest.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class ChatInfo {
    private final Long chatId;

    @JsonCreator
    public ChatInfo(@JsonProperty("chatId") Long chatId) {
        this.chatId = chatId;
    }

    public Long getChatId() {
        return chatId;
    }
}
