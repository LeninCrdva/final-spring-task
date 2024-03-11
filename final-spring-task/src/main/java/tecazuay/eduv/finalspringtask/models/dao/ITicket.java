package tecazuay.eduv.finalspringtask.models.dao;

import tecazuay.eduv.finalspringtask.models.entity.Ticket;

import java.util.List;

public interface ITicket {

    public List<Ticket> findAll();

    public Long save(Ticket ticket);

    public Ticket findOne(Long id);

    public void delete(Long id);
}
