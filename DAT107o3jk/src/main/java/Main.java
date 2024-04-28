import no.hvl.dat107.oblig3.*;

import java.util.List;

public class Main {

    private static AnsattDAO ansattDAO = new AnsattDAO();
    private static AvdelingDAO avdelingDAO = new AvdelingDAO();
    public static void main(String[] args) {
 
        System.out.println("Søker etter ansatt med Id:");
        System.out.println(ansattDAO.hentAnsattMedId(1));


        System.out.println("Søke etter ansatte med brukernavn (KnIn)");
        System.out.println(ansattDAO.hentAnsattMedBrukernavn("KnIn"));

        System.out.println("Henter ut alle ansatte");
        skrivUt();

        
        System.out.println("Oppdatering av stilling eller lønn (id 1)");
        System.out.println("Stilling:");
        System.out.println("Før: " + ansattDAO.hentAnsattMedId(1));
        ansattDAO.oppdaterAnsattStilling(1, "Graver");
        System.out.println("Etter: " + ansattDAO.hentAnsattMedId(1));
        System.out.println("Lønn:");
        System.out.println("Før: " + ansattDAO.hentAnsattMedId(1));
        ansattDAO.oppdaterAnsattLonn(1, 50);
        System.out.println("Etter: " + ansattDAO.hentAnsattMedId(1));

        System.out.println("Finn avdeling med id");
        System.out.println(avdelingDAO.finnAvdelingMedId(1));
         
        System.out.println("Finn avdeling med id");
        System.out.println(avdelingDAO.finnAvdelingMedId(1));


        System.out.println("List ansatte på avdeling og sjefen");
        System.out.println(avdelingDAO.hentAnsattePaaAvdeling(1));

        System.out.println("Oppdatere ansatt sin avdeling");
        System.out.println(ansattDAO.hentAnsattMedId(1));
        System.out.println("Du er også en graver no!");
        avdelingDAO.oppdaterAvdelingForAnsatt(1, 3);
        System.out.println(ansattDAO.hentAnsattMedId(1));

        System.out.println("Legg til ny avdeling");
        skrivUt();
        avdelingDAO.leggTilAvdeling("Bossspannet", 5);
        skrivUt();
         
    }
    private static void skrivUt() {

        List<Avdeling> avdelinger = avdelingDAO.hentAlleAvdelinger();

        avdelinger.forEach(System.out::println);
    }
}