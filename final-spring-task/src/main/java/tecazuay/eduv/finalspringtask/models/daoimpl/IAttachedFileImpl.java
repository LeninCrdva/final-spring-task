package tecazuay.eduv.finalspringtask.models.daoimpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import tecazuay.eduv.finalspringtask.models.dao.IAttachedFile;
import tecazuay.eduv.finalspringtask.models.entity.Attached_file;

import java.util.List;

@Repository
public class IAttachedFileImpl implements IAttachedFile {

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    @Override
    public List<Attached_file> findAll() {
        return em.createQuery("from Attached_file").getResultList();
    }

    @Override
    public void save(Attached_file file) {
        if(file.getCodeFile() != null && file.getCodeFile() > 0) {
            em.merge(file);
        } else {
            em.persist(file);
        }
    }

    @Override
    public Attached_file findOne(Long id) {
        return em.find(Attached_file.class, id);
    }

    @Override
    public Attached_file findOneByTicket(Long id){
        try {
            return em.createQuery("SELECT af FROM Attached_file af WHERE af.codeTicket.codeTicket = :id", Attached_file.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        em.remove(findOne(id));
    }
}
