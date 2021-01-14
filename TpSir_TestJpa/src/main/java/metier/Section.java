package metier;

import javax.persistence.*;
import java.util.List;

@Entity
public class Section {
    private long id;
    private String nomsection;
    private List<Fiche>fiches;

    public Section(){}

    public Section(List<Fiche>fiches, String nomsection ){
        this.fiches=fiches;
        this.nomsection=nomsection;
    }
    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @OneToMany(mappedBy = "section", cascade = CascadeType.PERSIST)
    public List<Fiche> getFiches() {
        return fiches;
    }

    public void setFiches(List<Fiche> fiches) {
        this.fiches = fiches;
    }

    public String getNomsection() {
        return nomsection;
    }

    public void setNomsection(String nomsection) {
        this.nomsection = nomsection;
    }
}
