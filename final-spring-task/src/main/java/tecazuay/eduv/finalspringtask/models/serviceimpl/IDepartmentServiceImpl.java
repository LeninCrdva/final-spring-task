package tecazuay.eduv.finalspringtask.models.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tecazuay.eduv.finalspringtask.models.dao.IDepartment;
import tecazuay.eduv.finalspringtask.models.entity.Department;
import tecazuay.eduv.finalspringtask.models.service.IDepartmentService;

import java.util.List;

@Service
public class IDepartmentServiceImpl implements IDepartmentService {

    @Autowired
    private IDepartment departmentDao;

    @Override
    @Transactional(readOnly = true)
    public List<Department> findAll() {
        return departmentDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Department> findAllDepartmentsByCompanyId(Long id){ return departmentDao.findAllDepartmentsByCompanyId(id); }

    @Override
    @Transactional
    public void save(Department department) {
        departmentDao.save(department);
    }

    @Override
    @Transactional(readOnly = true)
    public Department findOne(Long id) {
        return departmentDao.findOne(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        departmentDao.delete(id);
    }
}
