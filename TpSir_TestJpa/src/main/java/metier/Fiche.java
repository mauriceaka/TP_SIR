package metier;


import javax.persistence.*;
import java.sql.Date;
import java.util.*;

@Entity
public class Fiche {
    private long id;
    private String libelle;
    private java.sql.Date datebutoire;
    private User user;
    private java.sql.Date time;
    private String lieu;
    private String note;
    private String url;
    private List<Tag> tag;
    private Section section;
    
    public Fiche(){ }
    public Fiche( String libelle, java.sql.Date datebutoire,java.sql.Date time, String lieu, String url,List<Tag>tag, String note,Section section){
        this.libelle = libelle;
        this. datebutoire= datebutoire;
        this. time= time;
        this. lieu= lieu;
        this. note= note;
        this. tag= tag;
        this.url=url;
        this.section=section;
    }
    public Fiche( String libelle, java.sql.Date datebutoire, User user, java.sql.Date time, String lieu, String url,List<Tag>tag, String note,Section section){
        this.libelle = libelle;
        this. datebutoire= datebutoire;
        this. user= user;
        this. time= time;
        this. lieu= lieu;
        this. note= note;
        this. tag= tag;
        this.url=url;
        this.section=section;
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


    public java.sql.Date getdatebutoire() {
        return datebutoire;
    }

    public void setdatebutoire(java.sql.Date datebutoire) {
        this.datebutoire = datebutoire;
    }
    @ManyToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public java.sql.Date getTime() {
        return time;
    }

    public void setTime(java.sql.Date time) {
        this.time = time;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


    @ManyToMany(mappedBy = "fiche", cascade = CascadeType.PERSIST)
    public List<Tag> getTag() {
        return tag;
    }

    public void setTag(List<Tag> tag) {
        this.tag = tag;
    }

    @ManyToOne
    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }
    //    @Override
//    public String toString(){
//        return "Fiche [id=" + id + ", libelle=" + libelle + ", fiche=" + getLibelle() +"]";
//    }

   
}
