package Jpa;



import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import metier.Fiche;
import metier.User;

public class Joinfetch {

    private EntityManager manager;

    public Joinfetch(EntityManager manager) {
        this.manager = manager;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence
                .createEntityManagerFactory("withoutcreate");
        EntityManager manager = factory.createEntityManager();
        Joinfetch test = new Joinfetch(manager);


        TypedQuery<User> q = test.manager.createQuery("select distinct u from User u join fetch u.fiche f", User.class);
        long start = System.currentTimeMillis();
        List<User> res = q.getResultList();

        for (User u : res){
            for (Fiche f : u.getFiche()){
                f.getLibelle();
            }
        }

        long end = System.currentTimeMillis();
        long duree = end - start;
        System.err.println("temps d'exec = " +  duree);

        // TODO persist entity


        // TODO run request

        System.out.println(".. done");
    }

}

