package net.onlineconsultations.dao;

import java.util.List;

import net.onlineconsultations.domain.Chat;
import net.onlineconsultations.domain.ChatMessage;

public interface ChatMessageDAO {
    List<ChatMessage> getAll(Chat chat);

    ChatMessage getById(Long id);

    List<ChatMessage> getMessages(Chat chat, ChatMessage lastMessage);

    void save(ChatMessage chatMessage);
}
