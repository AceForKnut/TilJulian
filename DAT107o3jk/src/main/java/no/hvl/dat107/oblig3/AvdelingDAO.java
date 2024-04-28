package no.hvl.dat107.oblig3;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class AvdelingDAO{

    private EntityManagerFactory emf;

    public AvdelingDAO(){

        emf = Persistence.createEntityManagerFactory("oblig3jk");
    }

    
    public Avdeling finnAvdelingMedId(int id){

        EntityManager em = emf.createEntityManager();

        try{

            return em.find(Avdeling.class, id);
        }finally {

            em.close();
        }
    }

    
    public List<String> hentAnsattePaaAvdeling(int avdelingid){

        EntityManager em = emf.createEntityManager();

        String jpqlQuery = "select a from Ansatt a";
        List<String> avdeling = new ArrayList<>();

        try{

            TypedQuery<Ansatt> query = em.createQuery(jpqlQuery, Ansatt.class);
            for (Ansatt ansatt : query.getResultList()){

                if(ansatt.getAvdeling().getAvdelingid() == avdelingid){
                    if(ansatt.erSjef(ansatt)){

                        avdeling.add("Brukernavn: "+ ansatt.getBrukernavn() + " "
                                + "Fornavn: " + ansatt.getFornavn() +  " "
                                + "Stilling: " + ansatt.getStilling() + "(Sjef)");
                    }else{
                        avdeling.add("Brukernavn: "+ ansatt.getBrukernavn() + " "
                                + "Fornavn: " + ansatt.getFornavn() +  " "
                                + "Stilling: " + ansatt.getStilling());
                    }
                }
            }

            return avdeling;
        }finally {

            em.close();
        }
    }

    
    public void oppdaterAvdelingForAnsatt(int id, int avdelingid){

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{

            tx.begin();
            Ansatt ansatt = em.find(Ansatt.class, id);
            Avdeling avdeling = em.find(Avdeling.class, avdelingid);
            if(ansatt.erSjef(ansatt)){

                System.out.println("Kan ikke oppdatere avdeling for sjef");
            }else {

                ansatt.setAvdeling(avdeling);
                tx.commit();
            }
        }catch(Throwable e){

            e.printStackTrace();
            tx.rollback();
        }finally {

            em.close();
        }
    }

    
    public void leggTilAvdeling(String avdelingsnavn, int sjef){

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        Avdeling avdeling = new Avdeling();
        try{

            tx.begin();
            Ansatt nySjef = em.find(Ansatt.class, sjef);
            if(nySjef.erSjef(nySjef)){

                System.out.println("Denne ansatte er sjef fra før, prøv en annen");
            } else {

                avdeling.setAvdelingsnavn(avdelingsnavn);
                avdeling.setAvdelingssjef(nySjef);
                em.persist(avdeling);
                tx.commit();
            }

        }catch (Throwable e) {

            e.printStackTrace();
            tx.rollback();
        }finally {

            em.close();
        }
    }

    
    public List<Avdeling> hentAlleAvdelinger(){

        EntityManager em = emf.createEntityManager();

        String jpqlQuery = "select a from Avdeling a";

        try{

            TypedQuery<Avdeling> query = em.createQuery(jpqlQuery, Avdeling.class);
            return query.getResultList();
        }finally{

            em.close();
        }
    }
}