package tecazuay.eduv.finalspringtask.models.daoimpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import tecazuay.eduv.finalspringtask.models.dao.ICompany;
import tecazuay.eduv.finalspringtask.models.entity.Company;

import java.util.List;

@Repository
public class ICompanyImpl implements ICompany {

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    @Override
    public List<Company> findAll() {
        return em.createQuery("from Company ").getResultList();
    }

    @Override
    public void save(Company company) {
        if (company.getCodeCompany() != null && company.getCodeCompany() > 0) {
            em.merge(company);
        } else {
            em.persist(company);
        }
    }

    @Override
    public Company findOne(Long id) {
        return em.find(Company.class, id);
    }

    @Override
    public void delete(Long id) {
        em.remove(findOne(id));
    }
}
