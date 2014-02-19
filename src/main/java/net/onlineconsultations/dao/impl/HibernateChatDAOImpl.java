package net.onlineconsultations.dao.impl;

import net.onlineconsultations.dao.ChatDAO;
import net.onlineconsultations.domain.Chat;
import net.onlineconsultations.domain.ChatStatus;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
    public Chat findBySessionId(String sessionId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Chat> cq = cb.createQuery(Chat.class);
        Root<Chat> root = cq.from(Chat.class);
        cq.where(
                cb.equal(root.get("sessionId"), sessionId)
        );

        try {
            return em.createQuery(cq).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Chat findActiveChatWithConsultant(Long consultantId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Chat> cq = cb.createQuery(Chat.class);
        Root<Chat> root = cq.from(Chat.class);
        cq.where(cb.and(
                cb.equal(root.get("status"), ChatStatus.ACTIVE),
                cb.equal(root.get("consultantInChat").get("id"), consultantId)
            )
        );

        try {
            return em.createQuery(cq).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
