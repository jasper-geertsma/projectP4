public class Kantine {
    private Kassa kassa;
    private KassaRij kassarij;
    private KantineAanbod kantineAanbod;

    /**
     * Constructor
     */
    public Kantine() {
        kassarij = new KassaRij();
        kassa = new Kassa(kassarij);

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
}
