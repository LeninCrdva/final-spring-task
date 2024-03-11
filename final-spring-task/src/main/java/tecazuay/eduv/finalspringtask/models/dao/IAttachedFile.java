package tecazuay.eduv.finalspringtask.models.dao;

import tecazuay.eduv.finalspringtask.models.entity.Attached_file;

import java.util.List;

public interface IAttachedFile {

    public List<Attached_file> findAll();

    public void save(Attached_file file);

    public Attached_file findOne(Long id);

    public Attached_file findOneByTicket(Long id);

    public void delete(Long id);
}
