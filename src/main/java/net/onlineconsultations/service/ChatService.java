package net.onlineconsultations.service;

import net.onlineconsultations.domain.Chat;
import net.onlineconsultations.domain.ChatMessage;
import net.onlineconsultations.domain.User;

import java.util.List;

public interface ChatService {
    Chat startNewChat(User consultantInChat);

    void endChat(Chat chat);

    void postNewMessage(ChatMessage chatMessage);

    void setConsultantInChat(Chat chat);

    List<ChatMessage> getLastMessagesByChat(Chat chat, ChatMessage lastMessage);
    List<ChatMessage> getLastMessagesByChat(Chat chat);

    Chat findActiveChatWithConsultant(User consultant);

    ChatMessage getChatMessageById(Long id);

    Chat findBySessionId(String sessionId);
}
