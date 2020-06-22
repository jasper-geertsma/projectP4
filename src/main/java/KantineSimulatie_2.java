import java.util.*;

import java.util.Arrays;
import javax.persistence.Persistence;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

public class KantineSimulatie_2 {

    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("KantineSimulatie");
    private final EntityManager manager;

    // kantine
    private final Kantine kantine;

    // kantineaanbod
    private final KantineAanbod kantineaanbod;

    // random generator
    private final Random random;

    protected String dagAanbieding;

    // aantal artikelen
    private static final int AANTAL_ARTIKELEN = 4;

    // artikelen
    private static final String[] artikelnamen =
        new String[] {"Koffie", "Broodje pindakaas", "Broodje kaas", "Appelsap"};

    // prijzen
    private static final double[] artikelprijzen = new double[] {1.50, 2.10, 1.65, 1.65};

    // minimum en maximum aantal artikelen per soort
    private static final int MIN_ARTIKELEN_PER_SOORT = 10000;
    private static final int MAX_ARTIKELEN_PER_SOORT = 20000;

    // minimum en maximum aantal personen per dag
    private static final int MIN_PERSONEN_PER_DAG = 1;
    private static final int MAX_PERSONEN_PER_DAG = 500;

    public static final int DAGEN = 7;

    public static int huidige_dag = 0;
    // minimum en maximum artikelen per persoon
    private static final int MIN_ARTIKELEN_PER_PERSOON = 1;
    private static final int MAX_ARTIKELEN_PER_PERSOON = 4;

    /**
     * Constructor
     *
     */
    public KantineSimulatie_2() {
        manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        kantine = new Kantine(manager);
        random = new Random();
        String DagAanbieding= "";
        int[] hoeveelheden =
            getRandomArray(AANTAL_ARTIKELEN, MIN_ARTIKELEN_PER_SOORT, MAX_ARTIKELEN_PER_SOORT);
        kantineaanbod = new KantineAanbod(artikelnamen, artikelprijzen, hoeveelheden);

        kantine.setKantineAanbod(kantineaanbod);
    }

    /**
     * Methode om een array van random getallen liggend tussen min en max van de gegeven lengte te
     * genereren
     *
     * @param lengte
     * @param min
     * @param max
     * @return De array met random getallen
     */
    private int[] getRandomArray(int lengte, int min, int max) {
        int[] temp = new int[lengte];
        for (int i = 0; i < lengte; i++) {
            temp[i] = getRandomValue(min, max);
        }

        return temp;
    }

    /**
     * Methode om een random getal tussen min(incl) en max(incl) te genereren.
     *
     * @param min
     * @param max
     * @return Een random getal
     */
    private int getRandomValue(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    /**
     * Methode om op basis van een array van indexen voor de array artikelnamen de bijhorende array
     * van artikelnamen te maken
     *
     * @param indexen
     * @return De array met artikelnamen
     */
    private String[] geefArtikelNamen(int[] indexen) {
        String[] artikelen = new String[indexen.length];

        for (int i = 0; i < indexen.length; i++) {
            artikelen[i] = artikelnamen[indexen[i]];

        }

        return artikelen;
    }

    /**
     * Deze methode simuleert een aantal dagen
     * in het verloop van de kantine
     *
     * @param dagen
     */
    public void simuleer(int dagen) {
        // for lus voor dagen
        int[] dagTotaalArtikelen = new int[dagen];
        double[] dagTotaalGeld = new double[dagen];
        for(int i = 0; i < dagen; i++) {
            //hier wordt de huidige dag gemaakt
            huidige_dag = i;

            int student = 0;
            int docent = 0;
            int kantineMed = 0;
            // bedenk hoeveel personen vandaag binnen lopen
            int aantalPersonen = getRandomValue(MIN_PERSONEN_PER_DAG, MAX_PERSONEN_PER_DAG);

            // Kies een random dagaanbieding
            dagAanbieding = artikelnamen[getRandomValue(0, artikelnamen.length-1)];

            // laat de personen maar komen...

            for (int j = 0; j < aantalPersonen; j++) {
                int nieuwPersoon = random.nextInt(100);
                Dienblad klant = new Dienblad();
                Persoon persoon = null;


                if(nieuwPersoon < 89){
                    persoon = new Student(41077889,"Jasper", "Geertsma",30, 11, 1999, 'M', 1,"ICT");

                    klant.setKlant(persoon);
                    student++;
                    System.out.println();
                    //System.out.println("Student: " + persoon);
                }
                else if(nieuwPersoon < 99) {
                    persoon = new Docent(51078439, "Calvin", "Krafft",28,4,2000,'M',"kaft", "ICT");

                    klant.setKlant(persoon);
                    docent++;
                    //System.out.println("Docent: " + persoon);
                }
                else {
                    persoon = new KantineMedewerker(911, "Daniel","Paars",  11,9,2001,'F', 911, true);

                    klant.setKlant(persoon);
                    kantineMed++;
                    //System.out.println("Kantinemedewerker: " + persoon);
                }
                //random betaalwijze
                int obama = getRandomValue(1,2);
                if (obama == 1){ persoon.setBetaalwijze(new Contant());}
                else {persoon.setBetaalwijze(new Pinpas());}

                persoon.getBetaalwijze().setSaldo(random.nextInt(17));

                // maak persoon en dienblad aan, koppel ze
                // en bedenk hoeveel artikelen worden gepakt
                int aantalartikelen = getRandomValue(MIN_ARTIKELEN_PER_PERSOON, MAX_ARTIKELEN_PER_PERSOON) ;

                // genereer de "artikelnummers", dit zijn indexen
                // van de artikelnamen
                int[] tepakken = getRandomArray(
                        aantalartikelen, 0, AANTAL_ARTIKELEN-1);

                // vind de artikelnamen op basis van
                // de indexen hierboven
                String[] artikelen = geefArtikelNamen(tepakken);

                // loop de kantine binnen, pak de gewenste
                // artikelen, sluit aan
                kantine.loopPakSluitAan(klant, artikelen);
                //System.out.println(Arrays.toString(artikelen));

            }

            // verwerk rij voor de kassa
            // druk de dagtotalen af en hoeveel personen binnen
            // zijn gekomen
            // reset de kassa voor de volgende dag
            kantine.verwerkRijVoorKassa();

            Kassa kassa = kantine.getKassa();
            dagTotaalArtikelen[i] = kantine.getKassa().aantalArtikelen();
            dagTotaalGeld[i] = Math.round(kassa.hoeveelheidGeldInKassa() * 100) / 100;

            System.out.println("\nDag "+ (i+1) +":");
            System.out.println("Aantal studenten: " + student + " Aantal docenten: " + docent + " Aantal kantinemedewerkers: " + kantineMed);
            System.out.println("Totaal artikelen: " + kassa.aantalArtikelen());
            System.out.printf("Geld in kassa: $%.2f", kassa.hoeveelheidGeldInKassa());
            kassa.resetKassa();
        }

        System.out.println("Database query's :");
        System.out.printf("\nTotaal omzet: €%.2f \n", totaleOmzet());
        System.out.println("\nDe totale omzet per artikel: ");

        for (Object[] artikel : totaleOmzetPerArtikel()) {
            System.out.println(Arrays.toString(artikel));
        }
        System.out.println("\nDe totale omzet per artikel, per dag:");
        for (Object[] artikel : totaleOmzetPerArtikelPerDag()) {
            System.out.println(Arrays.toString(artikel));
        }
        System.out.println("\nDe drie meest populaire artikelen: ");
        for (Object[] artikel : drieMeestPopulaireArtikelen()) {
            System.out.println(Arrays.toString(artikel));
        }
        System.out.println("\nDe drie artikelen met de hoogste omzet: ");
        for (Object[] artikel : drieArtikelenMetHoogsteOmzet()) {
            System.out.println(Arrays.toString(artikel));
        }

        manager.close();
        ENTITY_MANAGER_FACTORY.close();
    }

    /**
     * Bereken totale omzet van alle facturen
     * @return totale omzet van alle facturen
     */
    public double totaleOmzet() {
        Query query = manager.createQuery(
                "SELECT (SUM(totaal) - SUM(korting)) FROM Factuur"
        );
        return (Double) query.getSingleResult();
    }

    /**
     * Verzamelt gegevens over de totale omzet per artikel
     * @return de totale omzetten van de artikelen
     */
    public List<Object[]> totaleOmzetPerArtikel() {
        Query query = manager.createQuery(
                "SELECT artikel.naam, SUM(artikel.prijs - artikel.korting) FROM FactuurRegel GROUP BY artikel.naam"
        );
        return query.getResultList();
    }

    /**
     * Verzamelt gegevens over de meest populaire artikelen
     * @return de drie meest populaire artikelen
     */
    public List<Object[]> drieMeestPopulaireArtikelen() {
        Query query = manager.createQuery(
                "SELECT artikel.naam, COUNT(artikel.naam) FROM FactuurRegel GROUP BY artikel.naam ORDER BY count(artikel.naam) DESC"
        );
        query.setMaxResults(3);
        return query.getResultList();
    }

    /**
     * Verzamelt gegevens over de totale omzet per artikel per dag
     * @return de totale omzetten van de artikelen per dag
     */
    public List<Object[]> totaleOmzetPerArtikelPerDag() {
        Query query = manager.createQuery(
                "SELECT f.datum, fr.artikel.naam, SUM(fr.artikel.prijs - fr.artikel.korting) FROM FactuurRegel fr JOIN fr.factuur f GROUP BY fr.artikel.naam, f.datum"
        );
        return query.getResultList();
    }

    /**
     * Verzamelt gegevens over de artikelen met de hoogste omzet
     * @return de drie artikelen met de hoogste omzet
     */
    public List<Object[]> drieArtikelenMetHoogsteOmzet() {
        Query query = manager
                .createQuery("SELECT artikel.naam, SUM(artikel.prijs - artikel.korting) FROM FactuurRegel GROUP BY artikel.naam ORDER BY SUM(artikel.prijs) DESC");
        query.setMaxResults(3);
        return query.getResultList();
    }


    public static void main(String[] args) {

        int dagen;
        if (args.length == 0) {
            dagen = DAGEN;
        } else {
            dagen = Integer.parseInt(args[0]);
        }

        KantineSimulatie_2 simulatie1 = new KantineSimulatie_2();
        simulatie1.simuleer(dagen);
    }
}
