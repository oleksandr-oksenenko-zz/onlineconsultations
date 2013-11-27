package net.onlineconsultations.dao;

import java.util.List;

import net.onlineconsultations.domain.ChatMessage;

public interface ChatMessageDAO {
    List<ChatMessage> getAll();

    ChatMessage getById(Long id);

    void save(ChatMessage chatMessage);
}
