package net.onlineconsultations.dao.impl;

import net.onlineconsultations.dao.ChatDAO;
import net.onlineconsultations.dao.exception.NoSuchRecordException;
import net.onlineconsultations.domain.Chat;
import net.onlineconsultations.domain.ChatStatus;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateChatDAOImpl extends HibernateBaseDAO<Chat> implements ChatDAO {
    public HibernateChatDAOImpl() {
        super(Chat.class);
    }

    @Override
    public Chat getActiveChatWithConsultant(Long consultantId) {
        Chat result = (Chat) sessionFactory.openSession()
                .createCriteria(Chat.class)
                .add(Restrictions.eq("status", ChatStatus.ACTIVE))
                .add(Restrictions.eq("consultantInChat.id", consultantId))
                .uniqueResult();
        if (result == null) {
            throw new NoSuchRecordException("Chat with id " + consultantId + " is not found");
        }
        return result;
    }
}
