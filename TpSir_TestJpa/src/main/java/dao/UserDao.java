package dao;

import metier.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class UserDao {

    private EntityManager manager;

    public UserDao(EntityManager manager) {
        this.manager = manager;
    }

    public void createUser(User user){
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        try {
            manager.persist(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        tx.commit();

    }

    public List<User> userList() {
        return manager.createQuery("Select a From User as a", User.class).getResultList();
    }

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
        EntityManager manager = factory.createEntityManager();
        UserDao userDao = new UserDao(manager);
        userDao.userList();
    }
}
