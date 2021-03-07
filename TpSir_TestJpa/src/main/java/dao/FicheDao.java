package dao;

import metier.Fiche;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class FicheDao {

    private EntityManager manager;

    public FicheDao(EntityManager manager) {
        this.manager = manager;
    }

    public void createFiche(Fiche fiche){
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        try {
            manager.persist(fiche);
        } catch (Exception e) {
            e.printStackTrace();
        }
        tx.commit();

    }

    public Fiche ficheById(Long id) {
        return manager.createQuery("Select a From Fiche a where a.id = :id", Fiche.class).setParameter("id", id).getSingleResult();
    }

    public List<Fiche> ficheList() {
        return manager.createQuery("Select a From Fiche as a", Fiche.class).getResultList();
    }
}
