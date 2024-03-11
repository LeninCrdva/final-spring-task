package tecazuay.eduv.finalspringtask.models.service;

import tecazuay.eduv.finalspringtask.models.entity.Company;

import java.util.List;

public interface ICompanyService {

    public List<Company> findAll();

    public void save(Company company);

    public Company findOne(Long id);

    public void delete(Long id);
}
