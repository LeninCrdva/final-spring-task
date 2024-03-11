package tecazuay.eduv.finalspringtask.models.service;

import tecazuay.eduv.finalspringtask.models.entity.Ticket_Case;

import java.util.List;

public interface ICaseService {

    public List<Ticket_Case> findAll();

    public void save(Ticket_Case ticketCase_ticket);

    public Ticket_Case findOne(Long id);

    public void delete(Long id);
}
