package metier;

import javax.persistence.*;
import java.util.List;

@Entity
public class Tag {
    private long id;
    private String libelle;
    private List<Fiche> fiche;

    public Tag(){}
    public Tag( String libelle){
        this.libelle=libelle;
    }


    public Tag( String libelle, List<Fiche> fiche){
        this.libelle=libelle;
        this.fiche=fiche;
    }

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @ManyToMany
    public List<Fiche> getFiche() {
        return fiche;
    }

    public void setFiche(List<Fiche> fiche) {
        this.fiche = fiche;
    }
}
