package net.onlineconsultations.service;

import java.util.List;

import net.onlineconsultations.domain.Chat;
import net.onlineconsultations.domain.ChatMessage;
import net.onlineconsultations.domain.User;

public interface ChatService {
    Chat startNewChat(User consultantInChat);

    void endChat(Chat chat);

    void postNewMessage(ChatMessage chatMessage);

    List<ChatMessage> getMessages(ChatMessage lastMessage);

    Chat getActiveChatWithConsultant(User consultant);

    Chat getChatById(Long id);

    ChatMessage getChatMessageById(Long id);

    void update(Chat chat);
}
