package net.onlineconsultations.dao.impl;

import net.onlineconsultations.dao.ChatDao;
import net.onlineconsultations.domain.Chat;
import net.onlineconsultations.domain.ChatStatus;
import net.onlineconsultations.domain.Consultant;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;

@Repository
public class HibChatDaoImpl extends HibGenericDaoImpl<Chat> implements ChatDao {
    public HibChatDaoImpl() {
        super(Chat.class);
    }

    @Override
    public Chat findBySessionId(String sessionId) {
        try {
            return em.createQuery("select c " +
                    " from Chat c " +
                    " where c.sessionId = :sessionId",
                    Chat.class)
                    .setParameter("sessionId", sessionId)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Chat findActiveChatWithConsultant(Consultant consultant) {
        try {
            return em.createQuery("select c " +
                    " from Chat c " +
                    " where c.status = :activeChatStatus " +
                    "  and c.consultantInChat = :consultant",
                    Chat.class)
                    .setParameter("activeChatStatus", ChatStatus.ACTIVE)
                    .setParameter("consultant", consultant)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
