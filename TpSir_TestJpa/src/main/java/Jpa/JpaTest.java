package Jpa;

import metier.*;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class JpaTest {

    private EntityManager manager;

    public JpaTest(EntityManager manager) {
        this.manager = manager;
    }

    /**
     * @param args default
     */

    public static void main(String[] args) {

        EntityManager manager = EntityManagerHelper.getEntityManager();
        JpaTest test = new JpaTest(manager);

        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        try {
            test.createFiche();
        } catch (Exception e) {
            e.printStackTrace();
        }
        tx.commit();

        test.listFiche();

        manager.close();
        System.out.println(".. done");

    }
    private void createFiche(){
        Tag tag1= new Tag("à couper en mai");
        List<Tag> tags1 = new ArrayList<>();
        tags1.add(tag1);
        manager.persist(tag1);
        java.sql.Date date= java.sql.Date.valueOf("2021-03-31");
        Section section1=new Section("important");
        manager.persist(section1);
        for (int j = 0; j < 2; j++) {

            EntityTransaction tx = manager.getTransaction();
            User u = new User("User"+j,"profession"+j);
            manager.persist(u);
            for (int i = 0; i < 2; i++) {
                Fiche f = new Fiche("fiche_"+j + "_"+i,date,date,"lieu"+j,"url"+j+ "_"+i,tags1,"note"+j+"_"+i,section1);
                manager.persist(f);
                f.setUser(u);
                u.getFiche().add(f);
            }

        }

}

    private void listFiche() {
        List<Fiche> resultList = manager.createQuery("Select a From Fiche as a", Fiche.class).getResultList();
        System.out.println("num of fiche:" + resultList.size());
        for (Fiche next : resultList) {
            System.out.println("next fiche: " + next);
        }
    }

}
