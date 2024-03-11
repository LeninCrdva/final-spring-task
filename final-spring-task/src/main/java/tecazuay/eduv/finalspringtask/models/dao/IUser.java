package tecazuay.eduv.finalspringtask.models.dao;

import tecazuay.eduv.finalspringtask.models.entity.User;

import java.util.List;

public interface IUser {
    
    public List<User> findAll();

    public void save(User user);

    public User findOne(String idCard);

    public void delete(String idCard);

    public Boolean login(String username, String password);
}
