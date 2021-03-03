package dao;

import metier.User;

import javax.enterprise.inject.spi.Bean;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
    public List<User> userList(EntityManager entityManager) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        Root<User> from = query.from(User.class);
        CriteriaQuery<User> selectUser=query.select(from);
        TypedQuery<User> typedQuery = entityManager.createQuery(selectUser);
        List<User> resultList = typedQuery.getResultList();
        return resultList;
    }
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
        EntityManager manager = factory.createEntityManager();
        UserDao userDao = new UserDao(manager);

        for (User user:userDao.userList(manager)) {
            System.out.println(user.getNom());
            System.out.println(user.getProfession());
            System.out.println(user.getFiche().get(0).getLibelle());

        }
    }
}
