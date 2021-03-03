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
     * @param args
     */

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
        EntityManager manager = factory.createEntityManager();
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
        for (int j = 0; j < 50; j++) {

            EntityTransaction tx = manager.getTransaction();
            User u = new User("User"+j,"profession"+j);
            manager.persist(u);
            for (int i = 0; i < 6; i++) {
                Fiche f = new Fiche("fiche_"+j + "_"+i,date,date,"lieu"+j,"url"+j+ "_"+i,tags1,"note"+j+"_"+i,section1);
                manager.persist(f);
                f.setUser(u);
                u.getFiche().add(f);
            }

        }

}

   /* private void createFiche()  {
//        int numOfFiche = manager.createQuery("Select a From Fiche a", Fiche.class).getResultList().size();
//        if (numOfFiche == 0) {

//
//SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
//Date date = simpleDateFormat.parse("25/12/2010");
//System.out.println(date);

//

//            Region regions = new Region();
//            regions.setNom("Lagunes");
//            manager.persist(regions);
//
//            Commune communes = new Commune();
//            communes.setNom("Cocody");
//            manager.persist(communes);
//
//            Mairie mairies = new Mairie();
//            mairies.setRegions(regions);
//            manager.persist(mairies);
//
//            mairies.setCommunes(communes);
//            manager.persist(mairies);
        for (int j = 0; j < 5000; j++) {

            EntityTransaction tx = manager.getTransaction();
            tx.begin();
            User u = new User("User"+j,"profession"+j);
            manager.persist(u);
            for (int i = 0; i < 2; i++) {
                Tag tag1= new Tag();
                List<Tag> tags1 = new ArrayList<>();
                tags1.add(tag1);
                java.sql.Date date= java.sql.Date.valueOf("2021-03-31");
                Section section1=new Section("important");
                Fiche f = new Fiche("fiche_"+j + "_"+i,date,date,"lieu"+j,"url"+j+ "_"+i,tags1,"note"+j+"_"+i,section1);
                manager.persist(f);
                f.setUser(u);
                u.getFiche().add(f);
            }
            tx.commit();

        }



/*

        Fiche fiche = new Fiche();
        fiche.setLibelle("fiche1");
        fiche.setLieu("ABIDJAN");
        fiche.setNote("test table fiche");
        fiche.setUrl("www.toast.com");
        manager.persist(fiche);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date datebutoire= null;
        try {
            datebutoire = simpleDateFormat.parse("14/01/2021");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        fiche.setdatebutoire(datebutoire);


        Tag tag = new Tag();
        tag.setLibelle("TEST PERSISTENCE");
        manager.persist(tag);



        User user = new User();
        user.setNom("REBECCA");
        user.setProfession("ingénieur BIG DATA");
        manager.persist(user);
        fiche.setUser(user);

        List<Tag> tags = new ArrayList<>();
        tags.add(tag);
        fiche.setTag(tags);

        // relation bidirectionnelle
        List<Fiche>fiches = new ArrayList<>();
        fiches.add(fiche);
        tag.setFiche(fiches);

        Section section = new Section();
        section.setNomsection("à faire");
        manager.persist(section);
        fiche.setSection(section);


        }*/
        /*List<Employee> resultList = manager.createQuery("Select a From Employee a", Employee.class).getResultList();
        System.out.println("num of employess:" + resultList.size());
        for (Employee next : resultList) {
            System.out.println("next employee: " + next);
        }

         */
 /*  public List<User> getUsers() {
       String query = "select u from User as u";
       return EntityManagerHelper.getEntityManager().createQuery(query, User.class).getResultList();
   }*/

    private void listFiche() {
        List<Fiche> resultList = manager.createQuery("Select a From Fiche as a", Fiche.class).getResultList();
        System.out.println("num of fiche:" + resultList.size());
        for (Fiche next : resultList) {
            System.out.println("next fiche: " + next);
        }
    }

}
