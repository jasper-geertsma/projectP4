import java.util.Iterator;

public class Kassa {

    private int totaalProducten;
    private double totaalGeld;
    private KassaRij kassarij;
    private Artikel artikel;

    /**
     * Constructor
     */
    public Kassa(KassaRij kassarij) {
        this.kassarij = kassarij;

    }

    /**
     * Vraag het aantal artikelen en de totaalprijs op. Tel deze gegevens op bij de controletotalen
     * die voor de kassa worden bijgehouden. De implementatie wordt later vervangen door een echte
     * betaling door de persoon.
     *
     * @param klant die moet afrekenen
     */
    public void rekenAf(Dienblad klant) {
        Persoon persoon = klant.getKlant();
        Betaalwijze betaalwijze = persoon.getBetaalwijze();
        double teBetalen = 0.0;

        double kortingDagaanbiedingen = 0.0d;
        double totaalPrijsArtikelen = 0.0d;
        int aantalArtikelen = 0;
//dsd
        
        if(persoon instanceof KortingskaartHouder){
            double korting = teBetalen * (((KortingskaartHouder)persoon).geefKortingsPercentage() * 0.01);
            if(((KortingskaartHouder)persoon).heeftMaximum()){
                if(korting > ((KortingskaartHouder)persoon).geefMaximum()){
                    korting = ((KortingskaartHouder)persoon).geefMaximum();
                }
            }
            korting = 100 + korting;
            //teBetalen -= korting;
            // Bereken de totaalprijs van de artikelen
            for (int y =0; y < klant.getAantalArtikelen();) {
                Artikel a = klant.artikelen.get(y);
                if(artikel.getKorting() != 0){
                    double nieuwePrijs = (artikel.getPrijs() / 100) + korting;
                }
                y++;
            }
            teBetalen = totaalPrijsDienblad(klant);

        }
        

         if(betaalwijze != null) {
             try{
                 if(persoon.getBetaalwijze() instanceof Pinpas) {
                     Pinpas p = (Pinpas) persoon.getBetaalwijze();
                     p.betaal(teBetalen);
                 } else if (persoon.getBetaalwijze() instanceof Contant) {
                     Contant c = (Contant) persoon.getBetaalwijze();
                     c.betaal(teBetalen);
                 }
                 totaalProducten += totaalArtikelenDienblad(klant);
                 totaalGeld += totaalPrijsDienblad(klant);
                 System.out.println(persoon.getVoornaam() + " " + persoon.getAchternaam() + " heeft betaald.");
             }
             catch(TeWeinigGeldException e){
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

    public int totaalArtikelenDienblad(Dienblad dienblad){
        int totaal = 0;
        if (dienblad.getIterator() == null) {
            System.out.println("Het dienblad is leeg");
            return totaal;
        }

        else{
            Iterator<Artikel> it = dienblad.getIterator();
            while (it.hasNext()) {
                it.next();
                totaal++;
            }
            return totaal;
        }

    }

    public double totaalPrijsDienblad(Dienblad dienblad){
        double totaal = 0.0;
        if (dienblad.getIterator() == null) {
            System.out.println("Het dienblad is leeg");
            return totaal;
        }

        else{
            Iterator<Artikel> it = dienblad.getIterator();
            while (it.hasNext()) {
                artikel = it.next();
                totaal += artikel.getPrijs();
            }
            return totaal;
        }
        
    }
    
    
}
