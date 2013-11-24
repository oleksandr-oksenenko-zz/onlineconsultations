package net.onlineconsultations.dao;

import java.util.List;

import net.onlineconsultations.domain.ChatMessage;

import org.springframework.dao.DataAccessException;

public interface ChatMessageDAO {
    List<ChatMessage> getAll() throws DataAccessException;

    ChatMessage get(Long id) throws DataAccessException;

    void save(ChatMessage subject)
            throws DataAccessException;
}
