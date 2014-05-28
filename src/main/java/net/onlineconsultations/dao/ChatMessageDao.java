package net.onlineconsultations.dao;

import net.onlineconsultations.domain.Chat;
import net.onlineconsultations.domain.ChatMessage;

import java.util.List;

public interface ChatMessageDao extends GenericDao<ChatMessage> {
    List<ChatMessage> getMessagesInChat(Chat chat);

    List<ChatMessage> getLastMessagesInChat(Chat chat, ChatMessage lastMessage);

    List<ChatMessage> getLastMessagesInChat(Chat chat, Long lastMessageId);
}
