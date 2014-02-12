package net.onlineconsultations.dao.impl;

import net.onlineconsultations.dao.ChatMessageDAO;
import net.onlineconsultations.domain.Chat;
import net.onlineconsultations.domain.ChatMessage;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class HibernateChatMessageDAOImpl extends HibernateBaseDAO<ChatMessage> implements ChatMessageDAO {
    @PersistenceContext
    private EntityManager em;


    public HibernateChatMessageDAOImpl() {
        super(ChatMessage.class);
    }

    @Override
    public List<ChatMessage> getLastMessagesByChat(Chat chat) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ChatMessage> cq = cb.createQuery(ChatMessage.class);
        Root<ChatMessage> chatMessage = cq.from(ChatMessage.class);
        cq.where(cb.equal(chatMessage.get("chat"), chat));

        return em.createQuery(cq).getResultList();
    }

    @Override
    public List<ChatMessage> getLastMessagesByChat(Chat chat, ChatMessage lastMessage) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ChatMessage> cq = cb.createQuery(ChatMessage.class);
        Root<ChatMessage> chatMessage = cq.from(ChatMessage.class);
        cq.where(
                cb.and(
                        cb.equal(chatMessage.get("chat"), chat),
                        cb.gt(chatMessage.<Number>get("id"), lastMessage.getId())
                )
        );

        return em.createQuery(cq).getResultList();
    }
}
