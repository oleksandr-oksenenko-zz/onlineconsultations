package net.onlineconsultations.dao;

import java.util.List;

import net.onlineconsultations.domain.Chat;

public interface ChatDAO {
    List<Chat> getAll();

    Chat getById(Long id);

    void save(Chat chat);

    void update(Chat chat);

    Chat getActiveChatWithConsultant(Long consultantId);
}
