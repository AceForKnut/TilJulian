package no.hvl.dat107.oblig3;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(schema = "oblig3jk", name = "ansatt")
public class Ansatt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ansattid;

    @Column
    //Unqiue
    private String brukernavn;

    @Column
    private String fornavn;

    @Column
    private String etternavn;

    @Column
    private Date ansettelse;

    @Column
    private String stilling;

    @Column
    private Integer manedslonn;

    //@Column
    @ManyToOne
    @JoinColumn(name = "avdeling", referencedColumnName = "avdelingid")
    private Avdeling avdeling;

    public Ansatt(String brukernavn, String fornavn, String etternavn, Date ansettelse, String stilling, int manedslonn, Avdeling avdeling){

        this.brukernavn = brukernavn;
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.ansettelse = ansettelse;
        this.stilling = stilling;
        this.manedslonn = manedslonn;
        this.avdeling = avdeling;
    }
    
    public Ansatt() {
        // Default constructor with no arguments
    }


    public Integer getAnsattid() {
        return ansattid;
    }

    public String getBrukernavn() {
        return brukernavn;
    }

    public void setBrukernavn(String brukernavn) {
        this.brukernavn = brukernavn;
    }

    public String getFornavn() {
        return fornavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public void setEtternavn(String etternavn) {
        this.etternavn = etternavn;
    }

    public Date getAnsettelse() {
        return ansettelse;
    }

    public void setAnsettelse(Date ansettelse) {
        this.ansettelse = ansettelse;
    }

    public String getStilling() {
        return stilling;
    }

    public void setStilling(String stilling) {
        this.stilling = stilling;
    }

    public Integer getManedslonn() {
        return manedslonn;
    }

    public void setManedslonn(Integer manedslonn) {
        this.manedslonn = manedslonn;
    }

    public Avdeling getAvdeling() {
        return avdeling;
    }

    public void setAvdeling(Avdeling avdeling) {
        this.avdeling = avdeling;
    }

    public boolean erSjef(Ansatt ansatt){

    	return ansatt.getAvdeling() != null && ansatt.getAvdeling().getAvdelingssjef() != null &&
    	           ansatt.equals(ansatt.getAvdeling().getAvdelingssjef());
    }

    @Override
    public String toString() {
        return "Ansatt{" +
                "id=" + ansattid +
                ", brukernavn='" + brukernavn + '\'' +
                ", fornavn='" + fornavn + '\'' +
                ", etternavn='" + etternavn + '\'' +
                ", stilling='" + stilling + '\'' +
                ", avdeling'" + avdeling.getAvdelingsnavn() + '\'' +
                '}';
    }
}
