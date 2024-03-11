package tecazuay.eduv.finalspringtask.models.dao;


import tecazuay.eduv.finalspringtask.models.entity.Company;

import java.util.List;

public interface ICompany {

    public List<Company> findAll();

    public void save(Company company);

    public Company findOne(Long id);

    public void delete(Long id);
}
