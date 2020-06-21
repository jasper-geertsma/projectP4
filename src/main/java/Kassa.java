import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.time.LocalDate;
import java.util.Iterator;

public class Kassa {

    private int totaalProducten;
    private double totaalGeld;
    private final KassaRij kassarij;
    private Artikel artikel;
    private EntityManager manager;

    /**
     * Constructor
     */
    public Kassa(KassaRij kassarij, EntityManager manager) {
        this.kassarij = kassarij;
        this.manager = manager;
    }

    /**
     * Vraag het aantal artikelen en de totaalprijs op. Tel deze gegevens op bij de controletotalen
     * die voor de kassa worden bijgehouden. De implementatie wordt later vervangen door een echte
     * betaling door de persoon.
     *
     * @param klant die moet afrekenen
     */
    public void rekenAf(Dienblad klant) {
        Factuur factuur = new Factuur(klant, LocalDate.now().plusDays(KantineSimulatie_2.huidige_dag));
        Persoon persoon = klant.getKlant();

        // Print uit hoe de klant gaat betalen
        if (persoon.getBetaalwijze() instanceof Contant) {
            System.out.println("De klant wil contant afrekenen.");
        }
        else {
            System.out.println("De klant wil met de pinpas afrekenen.");
        }

        Iterator<Artikel> artikelen = klant.getIterator();
        while (artikelen.hasNext()){
            Artikel a = artikelen.next();
            totaalProducten++;
        }

        kassarij.afgerondeKlant(klant);
        double totaalBedrag = factuur.getTotaal() - factuur.getKorting();

        EntityTransaction transaction = null;
         if(persoon.getBetaalwijze() != null) {
             try{
                 transaction = manager.getTransaction();
                 transaction.begin();

                 persoon.getBetaalwijze().betaal(totaalBedrag);

                 totaalGeld += totaalBedrag;
                 manager.persist(factuur);
                 transaction.commit();
                 //System.out.println(persoon.getVoornaam() + " " + persoon.getAchternaam() + " heeft betaald.");
             }
             catch(TeWeinigGeldException e){
                 if (transaction != null) {
                     transaction.rollback();
                 }
                 System.out.println(persoon.getVoornaam() + " " + persoon.getAchternaam() + " Heeft niet genoeg geld");
             }

        } else{
            System.out.println("Selecteer AUB een betaalwijze");
        }
    }





    /**
     * Geeft het aantal artikelen dat de kassa heeft gepasseerd, vanaf het moment dat de methode
     * resetWaarden is aangeroepen.
     *
     * @return aantal artikelen
     */
    public int aantalArtikelen() {
        return totaalProducten;
    }

    /**
     * Geeft het totaalbedrag van alle artikelen die de kass zijn gepasseerd, vanaf het moment dat
     * de methode resetKassa is aangeroepen.
     *
     * @return hoeveelheid geld in de kassa
     */
    public double hoeveelheidGeldInKassa() {
        return totaalGeld;
    }

    /**
     * reset de waarden van het aantal gepasseerde artikelen en de totale hoeveelheid geld in de
     * kassa.
     */
    public void resetKassa() {
        totaalGeld = 0;
        totaalProducten = 0;
    }
    
    
}
