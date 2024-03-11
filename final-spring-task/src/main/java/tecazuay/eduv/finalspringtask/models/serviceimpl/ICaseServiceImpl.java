package tecazuay.eduv.finalspringtask.models.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tecazuay.eduv.finalspringtask.models.dao.ICase;
import tecazuay.eduv.finalspringtask.models.entity.Ticket_Case;
import tecazuay.eduv.finalspringtask.models.service.ICaseService;

import java.util.List;

@Service
public class ICaseServiceImpl implements ICaseService {

    @Autowired
    private ICase caseDao;

    @Override
    @Transactional(readOnly = true)
    public List<Ticket_Case> findAll() {
        return caseDao.findAll();
    }

    @Override
    @Transactional
    public void save(Ticket_Case ticketCase_ticket) {
        caseDao.save(ticketCase_ticket);
    }

    @Override
    @Transactional(readOnly = true)
    public Ticket_Case findOne(Long id) {
        return caseDao.findOne(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        caseDao.delete(id);
    }
}
