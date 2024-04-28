package no.hvl.dat107.oblig3;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class AnsattJPA{

    private EntityManagerFactory emf;

    public AnsattJPA(){

        emf = Persistence.createEntityManagerFactory("oblig3jk");
    }

    
    public List<Ansatt> hentAnsatte(){

        EntityManager em = emf.createEntityManager();

        String jpqlQuery = "select a from Ansatt a";

        try{

            TypedQuery<Ansatt> query = em.createQuery(jpqlQuery, Ansatt.class);
            return query.getResultList();
        }finally{

            em.close();
        }
    }

    
    public Ansatt hentAnsattMedId(int id){

        EntityManager em = emf.createEntityManager();

        try{

            return em.find(Ansatt.class, id);
        }finally {

            em.close();
        }
    }
}
