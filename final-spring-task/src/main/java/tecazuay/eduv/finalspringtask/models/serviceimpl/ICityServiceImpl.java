package tecazuay.eduv.finalspringtask.models.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tecazuay.eduv.finalspringtask.models.dao.ICity;
import tecazuay.eduv.finalspringtask.models.entity.City;
import tecazuay.eduv.finalspringtask.models.service.ICityService;

import java.util.List;

@Service
public class ICityServiceImpl implements ICityService {

    @Autowired
    private ICity cityDao;

    @Override
    @Transactional(readOnly = true)
    public List<City> findAll() {
        return cityDao.findAll();
    }

    @Override
    @Transactional
    public void save(City city) {
        cityDao.save(city);
    }

    @Override
    @Transactional(readOnly = true)
    public City findOne(Long id) {
        return cityDao.findOne(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        cityDao.delete(id);
    }
}
