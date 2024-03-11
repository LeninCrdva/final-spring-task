package tecazuay.eduv.finalspringtask.models.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tecazuay.eduv.finalspringtask.models.dao.ICompany;
import tecazuay.eduv.finalspringtask.models.entity.Company;
import tecazuay.eduv.finalspringtask.models.service.ICompanyService;

import java.util.List;

@Service
public class ICompanyServiceImpl implements ICompanyService {

    @Autowired
    private ICompany companyDao;

    @Override
    @Transactional(readOnly = true)
    public List<Company> findAll() {
        return companyDao.findAll();
    }

    @Override
    @Transactional
    public void save(Company company) {
        companyDao.save(company);
    }

    @Override
    @Transactional(readOnly = true)
    public Company findOne(Long id) {
        return companyDao.findOne(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        companyDao.delete(id);
    }
}
