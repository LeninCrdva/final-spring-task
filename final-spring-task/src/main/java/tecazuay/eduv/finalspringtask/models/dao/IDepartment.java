package tecazuay.eduv.finalspringtask.models.dao;

import tecazuay.eduv.finalspringtask.models.entity.Department;

import java.util.List;

public interface IDepartment {
    public List<Department> findAll();

    public List<Department> findAllDepartmentsByCompanyId(Long id);

    public void save(Department department);

    public Department findOne(Long id);

    public void delete(Long id);
}
