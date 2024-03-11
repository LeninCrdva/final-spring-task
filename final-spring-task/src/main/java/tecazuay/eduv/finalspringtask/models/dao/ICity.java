package tecazuay.eduv.finalspringtask.models.dao;

import tecazuay.eduv.finalspringtask.models.entity.City;

import java.util.List;

public interface ICity {

    public List<City> findAll();

    public void save(City city);

    public City findOne(Long id);

    public void delete(Long id);
}
