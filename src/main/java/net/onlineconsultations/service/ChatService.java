package net.onlineconsultations.service;

import net.onlineconsultations.domain.Chat;
import net.onlineconsultations.domain.ChatMessage;
import net.onlineconsultations.domain.User;

import java.util.List;

public interface ChatService {
    Chat startChat(User consultantInChat);

    void endChat(Chat chat);

    void postNewMessage(ChatMessage chatMessage);

    List<ChatMessage> getLastMessagesByChat(Chat chat, ChatMessage lastMessage);

    Chat getActiveChatWithConsultant(User consultant);

    Chat getChatById(Long id);

    ChatMessage getChatMessageById(Long id);

    void update(Chat chat);
}
