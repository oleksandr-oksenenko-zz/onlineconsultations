package net.onlineconsultations.service;

import net.onlineconsultations.domain.Chat;
import net.onlineconsultations.domain.ChatMessage;
import net.onlineconsultations.domain.Consultant;

import java.util.List;

public interface ChatService {
    Chat startNewChat(Consultant consultantInChat);

    void endChatForAnonym(Chat chat);

    void endChatForConsultant(Chat chat, Consultant consultant);

    void postNewMessage(ChatMessage chatMessage);

    void setConsultantInChat(Chat chat, boolean isConsultantInChat);

    void setAnonymInChat(Chat chat, boolean isAnonymInChat);

    List<ChatMessage> getLastMessagesInChat(Chat chat, ChatMessage lastMessage);
    List<ChatMessage> getLastMessagesInChat(Chat chat, Long lastMessageId);
    List<ChatMessage> getMessagesInChat(Chat chat);

    Chat findActiveChatWithConsultant(Consultant consultant);

    Chat findBySessionId(String sessionId);
}
