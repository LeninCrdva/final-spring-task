package tecazuay.eduv.finalspringtask.models.daoimpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import tecazuay.eduv.finalspringtask.models.dao.ITicket;
import tecazuay.eduv.finalspringtask.models.entity.Ticket;

import java.util.List;

@Repository
public class ITicketImpl implements ITicket {

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    @Override
    public List<Ticket> findAll() {
        return em.createQuery("from Ticket").getResultList();
    }

    @Override
    public Long save(Ticket ticket) {
        if (ticket.getCodeTicket() != null && ticket.getCodeTicket() > 0) {
            Ticket oldTicket = findOne(ticket.getCodeTicket());
            ticket.setCreateAt(oldTicket.getCreateAt());
            em.merge(ticket);
            return ticket.getCodeTicket();
        } else {
            em.persist(ticket);
            em.flush();
            return ticket.getCodeTicket();
        }
    }

    @Override
    public Ticket findOne(Long id) {
        return em.find(Ticket.class, id);
    }

    @Override
    public void delete(Long id) {
        em.remove(findOne(id));
    }
}
