package tecazuay.eduv.finalspringtask.models.daoimpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import tecazuay.eduv.finalspringtask.models.dao.IUser;
import tecazuay.eduv.finalspringtask.models.entity.Attached_file;
import tecazuay.eduv.finalspringtask.models.entity.User;

import java.util.List;

@Repository
public class IUserImpl implements IUser {

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    @Override
    public List<User> findAll() {
        return em.createQuery("from User").getResultList();
    }

    @Override
    public void save(User user) {
        if (!user.getIdCard().isEmpty()) {
            em.merge(user);
        } else {
            em.persist(user);
        }
    }

    @Override
    public User findOne(String id) {
        return em.find(User.class, id);
    }

    @Override
    public void delete(String id) {
        em.remove(findOne(id));
    }

    @Override
    public Boolean login(String username, String password){
        try {
            User user = em.createQuery("SELECT us FROM User us WHERE us.idCard = :username AND us.password = :password", User.class)
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .getSingleResult();

            return user != null;
        } catch (NoResultException e) {
            return false;
        }
    }
}
