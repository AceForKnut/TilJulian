package no.hvl.dat107.oblig3;

import jakarta.persistence.*;

@Entity
@Table(schema = "oblig3jk", name = "avdeling")
public class Avdeling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int avdelingid;

    @Column
    private String avdelingsnavn;

    //@Column
    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "avdelingssjef", referencedColumnName = "ansattid")
    private Ansatt avdelingssjef;

    public Avdeling(){}

    public int getAvdelingid() {
        return avdelingid;
    }

    public String getAvdelingsnavn() {
        return avdelingsnavn;
    }

    public void setAvdelingsnavn(String avdelingsnavn) {
        this.avdelingsnavn = avdelingsnavn;
    }

    public Ansatt getAvdelingssjef() {
        return avdelingssjef;
    }

    public void setAvdelingssjef(Ansatt avdelingssjef) {
        this.avdelingssjef = avdelingssjef;
    }

    @Override
    public String toString() {
    	return "Avdeling{" +
                "avdelingid=" + avdelingid +
                ", avdelingsnavn='" + avdelingsnavn + '\'' +
                ", avdelingssjef=" + (avdelingssjef != null ? avdelingssjef.getBrukernavn() : "null") + '}'; // I tilfeller der det IKKE er en sjef av en eller annen grunn
    }
}
