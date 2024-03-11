package tecazuay.eduv.finalspringtask.models.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tecazuay.eduv.finalspringtask.models.dao.ITicket;
import tecazuay.eduv.finalspringtask.models.entity.Ticket;
import tecazuay.eduv.finalspringtask.models.service.ITicketService;

import java.util.List;

@Service
public class ITicketServiceImpl implements ITicketService {

    @Autowired
    private ITicket ticketDao;

    @Override
    @Transactional(readOnly = true)
    public List<Ticket> findAll() {
        return ticketDao.findAll();
    }

    @Override
    @Transactional
    public Long save(Ticket ticket) {
        return ticketDao.save(ticket);
    }

    @Override
    @Transactional(readOnly = true)
    public Ticket findOne(Long id) {
        return ticketDao.findOne(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        ticketDao.delete(id);
    }
}
