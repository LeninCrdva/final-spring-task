package tecazuay.eduv.finalspringtask.models.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tecazuay.eduv.finalspringtask.models.dao.IUser;
import tecazuay.eduv.finalspringtask.models.entity.User;
import tecazuay.eduv.finalspringtask.models.service.IUserService;

import java.util.List;

@Service
public class IUserServiceImpl implements IUserService {

    @Autowired
    private IUser userDao;

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    @Transactional
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User findOne(String id) {
        return userDao.findOne(id);
    }

    @Override
    @Transactional
    public void delete(String id) {
        userDao.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean login(String username, String password) {
        return userDao.login(username, password);
    }
}
