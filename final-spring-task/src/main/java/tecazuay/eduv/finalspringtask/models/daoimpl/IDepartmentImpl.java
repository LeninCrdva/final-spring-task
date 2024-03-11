package tecazuay.eduv.finalspringtask.models.daoimpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import tecazuay.eduv.finalspringtask.models.dao.IDepartment;
import tecazuay.eduv.finalspringtask.models.entity.Department;

import java.util.List;

@Repository
public class IDepartmentImpl implements IDepartment {

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    @Override
    public List<Department> findAll() {
        return em.createQuery("from Department").getResultList();
    }

    @Override
    public List<Department> findAllDepartmentsByCompanyId(Long id){
        try {
            return em.createQuery("SELECT d FROM Department d WHERE d.codeCompany.codeCompany = :id", Department.class)
                    .setParameter("id", id)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void save(Department department) {
        if (department.getCodeDepartment() != null && department.getCodeDepartment() > 0) {
            em.merge(department);
        } else {
            em.persist(department);
        }
    }

    @Override
    public Department findOne(Long id) {
        return em.find(Department.class, id);
    }

    @Override
    public void delete(Long id) {
        em.remove(findOne(id));
    }
}
