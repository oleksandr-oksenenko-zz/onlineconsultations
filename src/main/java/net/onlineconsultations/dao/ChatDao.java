package net.onlineconsultations.dao;

import net.onlineconsultations.domain.Chat;
import net.onlineconsultations.domain.Consultant;

public interface ChatDao extends GenericDao<Chat> {
    Chat findActiveChatWithConsultant(Consultant consultant);
    Chat findBySessionId(String sessionId);
}
