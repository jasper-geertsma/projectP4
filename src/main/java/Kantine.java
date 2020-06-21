import javax.persistence.EntityManager;

public class Kantine {
    private final Kassa kassa;
    private final KassaRij kassarij;
    private KantineAanbod kantineAanbod;
    private EntityManager manager;

    /**
     * Constructor
     */
    public Kantine(EntityManager manager) {
        kassarij = new KassaRij();
        kassa = new Kassa(kassarij, manager);
        this.manager = manager;
    }

    /**
     * In deze methode wordt een Persoon en Dienblad gemaakt en aan elkaar gekoppeld. Maak twee
     * Artikelen aan en plaats deze op het dienblad. Tenslotte sluit de Persoon zich aan bij de rij
     * voor de kassa.
     */
    public void loopPakSluitAan(Dienblad dienblad, String[] artikelnamen) {
        for (String artikelnaam : artikelnamen){
            dienblad.voegToe(kantineAanbod.getArtikel(artikelnaam));
        }

        kassarij.sluitAchteraan(dienblad);
    }

    /**
     * Deze methode handelt de rij voor de kassa af.
     */
    public void verwerkRijVoorKassa() {
        while (kassarij.erIsEenRij() == true) {
            Dienblad klant = kassarij.eerstePersoonInRij();
            kassa.rekenAf(klant);
        }
    }

    public Kassa getKassa(){
        return kassa;
    }

    public void setKantineAanbod(KantineAanbod kantineAanbod) {
        this.kantineAanbod = kantineAanbod;
    }

    public KantineAanbod getKantineAanbod() {
        return kantineAanbod;
    }

    public void setDagAanbieding(String dagAanbieding) {
        kantineAanbod.setDagAanbieding(dagAanbieding);
    }
}
