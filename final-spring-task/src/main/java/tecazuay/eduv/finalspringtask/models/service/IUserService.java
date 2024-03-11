package tecazuay.eduv.finalspringtask.models.service;

import tecazuay.eduv.finalspringtask.models.entity.User;

import java.util.List;

public interface IUserService {
    public List<User> findAll();

    public void save(User user);

    public User findOne(String id);

    public void delete(String id);

    public Boolean login(String username, String password);
}
