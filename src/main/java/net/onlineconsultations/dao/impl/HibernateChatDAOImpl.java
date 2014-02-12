package net.onlineconsultations.dao.impl;

import net.onlineconsultations.dao.ChatDAO;
import net.onlineconsultations.dao.exception.NoSuchRecordException;
import net.onlineconsultations.domain.Chat;
import net.onlineconsultations.domain.ChatStatus;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class HibernateChatDAOImpl extends HibernateBaseDAO<Chat> implements ChatDAO {
    @PersistenceContext
    private EntityManager em;

    public HibernateChatDAOImpl() {
        super(Chat.class);
    }

    @Override
    public Chat getActiveChatWithConsultant(Long consultantId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Chat> cq = cb.createQuery(Chat.class);
        Root<Chat> root = cq.from(Chat.class);
        cq.where(cb.and(
                cb.equal(root.get("status"), ChatStatus.ACTIVE),
                cb.equal(root.get("consultantInChat.id"), consultantId)
        )
        );

        Chat result = em.createQuery(cq).getSingleResult();

        if (result == null) {
            throw new NoSuchRecordException("Chat with id " + consultantId + " is not found");
        }
        return result;
    }
}
