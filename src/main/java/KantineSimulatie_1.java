import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
public class KantineSimulatie_1 {
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("KantineSimulatie");
    private EntityManager manager;

    private final Kantine kantine;

    public static final int DAGEN = 7;

    /**
     * Constructor
     */
    public KantineSimulatie_1() {
        kantine = new Kantine(manager);
    }

    /**
     * Deze methode simuleert een aantal dagen in het
     * verloop van de kantine
     *
     * @param dagen
     */
    public void simuleer(int dagen) {
        double dagGeld = 0;
        int dagArtikelen = 0;
        // herhaal voor elke dag
        Dienblad dienblad = new Dienblad();
        for (int i = 0;dagen >= i; i++) {

            // per dag nu even vast 10 + i personen naar binnen
            // laten gaan, wordt volgende week veranderd...

            // for lus voor personen
            for (int j = 0; j < 10 + i; j++) {
                //kantine.loopPakSluitAan(); vragen aan de docent. !!
            }

            // verwerk rij voor de kassa
            kantine.verwerkRijVoorKassa();

            // toon dagtotalen (artikelen en geld in kassa)
            dagGeld = kantine.getKassa().hoeveelheidGeldInKassa();
            dagArtikelen = kantine.getKassa().aantalArtikelen();

            System.out.println("Dag " + i + ": Totaal aantal artikelen verkocht: " + dagArtikelen + ". Totale verdiensten van vandaag: " + dagGeld);

            // reset de kassa voor de volgende dag
            kantine.getKassa().resetKassa();
        }
    }

    /**
     * Start een simulatie
     */
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
