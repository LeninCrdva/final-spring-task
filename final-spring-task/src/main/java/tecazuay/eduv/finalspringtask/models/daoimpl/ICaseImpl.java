package tecazuay.eduv.finalspringtask.models.daoimpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import tecazuay.eduv.finalspringtask.models.dao.ICase;
import tecazuay.eduv.finalspringtask.models.entity.Ticket_Case;

import java.util.List;

@Repository
public class ICaseImpl implements ICase {

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    @Override
    public List<Ticket_Case> findAll() {
        return em.createQuery("from Ticket_Case").getResultList();
    }

    @Override
    public void save(Ticket_Case ticketCase_ticket) {
        if (ticketCase_ticket.getCodeCase() != null && ticketCase_ticket.getCodeCase() > 0) {
            em.merge(ticketCase_ticket);
        } else {
            em.persist(ticketCase_ticket);
        }
    }

    @Override
    public Ticket_Case findOne(Long id) {
        return em.find(Ticket_Case.class, id);
    }

    @Override
    public void delete(Long id) {
        em.remove(findOne(id));
    }
}
