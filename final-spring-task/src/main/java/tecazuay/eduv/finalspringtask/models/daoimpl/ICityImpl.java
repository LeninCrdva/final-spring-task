package tecazuay.eduv.finalspringtask.models.daoimpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import tecazuay.eduv.finalspringtask.models.dao.ICity;
import tecazuay.eduv.finalspringtask.models.entity.City;

import java.util.List;

@Repository
public class ICityImpl implements ICity {

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    @Override
    public List<City> findAll() {
        return em.createQuery("from City").getResultList();
    }

    @Override
    public void save(City city) {
        if (city.getCodeCity() != null && city.getCodeCity() > 0) {
            em.merge(city);
        } else {
            em.persist(city);
        }
    }

    @Override
    public City findOne(Long id) {
        return em.find(City.class, id);
    }

    @Override
    public void delete(Long id) {
        em.remove(findOne(id));
    }
}
