package net.onlineconsultations.dao;

import net.onlineconsultations.domain.Chat;

public interface ChatDAO extends GenericDao<Chat> {
    Chat getActiveChatWithConsultant(Long consultantId);
    Chat findBySessionId(String sessionId);
}
