package metier;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    private long id;
    private String nom;
    private String profession;
    private List<Fiche> fiche=new ArrayList<Fiche>();

    public User(){}

    public User( String nom, String profession, List<Fiche> fiche){
        this.nom = nom;
        this.profession = profession;
        this.fiche = fiche;
    }
    public User( String nom, String profession){
        this.nom = nom;
        this.profession = profession;
    }

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    public List<Fiche> getFiche() {
        return fiche;
    }

    public void setFiche(List<Fiche> fiche) {
        this.fiche = fiche;
    }
}
