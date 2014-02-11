package net.onlineconsultations.dao;

import net.onlineconsultations.domain.Chat;
import net.onlineconsultations.domain.ChatMessage;

import java.util.List;

public interface ChatMessageDAO extends GenericDao<ChatMessage> {
    List<ChatMessage> getLastMessagesByChat(Chat chat, ChatMessage lastMessage);
}
