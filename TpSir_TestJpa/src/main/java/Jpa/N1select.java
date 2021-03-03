package Jpa;

import metier.Fiche;
import metier.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class N1select {

        private EntityManager manager;

        public N1select(EntityManager manager) {
            this.manager = manager;
        }

        /**
         * @param args
         */
        public static void main(String[] args) {
            EntityManagerFactory factory = Persistence
                    .createEntityManagerFactory("withoutcreate");
            EntityManager manager = factory.createEntityManager();
            N1select test = new N1select(manager);


            TypedQuery<User> q = test.manager.createQuery("select u from User u",User.class);
            long start = System.currentTimeMillis();
            List<User> res = q.getResultList();
            res.forEach(user -> {   for (Fiche f : user.getFiche()){
                f.getLibelle();
            }});


            long end = System.currentTimeMillis();
            long duree = end - start;
            System.err.println("temps d'exec = " +  duree);

            // TODO persist entity


            // TODO run request

            System.out.println(".. done");
        }

}
