import java.time.LocalDate;
import java.io.Serializable;
import java.util.Iterator;

public class Factuur implements Serializable {
    private Long id;
    private LocalDate datum;
    private double korting;
    private double totaal;

    public Factuur() {
        totaal = 0;
        korting = 0;
    }

    public Factuur(Dienblad klant, LocalDate datum) {
        this();
        this.datum = datum;

        verwerkBestelling(klant);
}

/**
    * Verwerk artikelen en pas kortingen toe.
    *
    * Zet het totaal te betalen bedrag en het
    * totaal aan ontvangen kortingen.
    *
    * @param klant
    */
private void verwerkBestelling(Dienblad klant) {
    Iterator<Artikel> artikel = klant.getArtikelen();
    double kortingDagaanbiedingen = 0.0d;
    double totaalPrijsArtikelen = 0.0d;
    Persoon persoon = klant.getKlant();
    // Bereken de totaalprijs van de artikelen
    while (artikel.hasNext()) {
        Artikel a = artikel.next();
        totaalPrijsArtikelen += a.getPrijs();
        kortingDagaanbiedingen += a.getKorting();
        // Voeg factuurregel toe
        //regels.add(new FactuurRegel(this, a));
    }

    // Check voor korting en bereken de korting
    if (persoon instanceof KortingskaartHouder) {
        KortingskaartHouder kh = (KortingskaartHouder)persoon;
        korting = totaalPrijsArtikelen / 100.0d * kh.geefKortingsPercentage();
        if (kh.heeftMaximum() && korting > kh.geefMaximum()) {
            korting = ((KortingskaartHouder) persoon).geefMaximum();
        }
    }
    korting += kortingDagaanbiedingen;
    totaal = totaalPrijsArtikelen;
}

/*
    * @return het totaalbedrag
 */
public double getTotaal() {
    return totaal;
}

/**
  * @return de toegepaste korting
*/
public double getKorting() {
    return korting;
}

/**
  * @return een printbaar bonnetje
*/
public String toString() {
    // method body omitted
    String bon = "";
    Iterator<FactuurRegel> it = regels.iterator();
    while (it.hasNext()) {
        FactuurRegel fr = it.next();
        bon += String.format("%s\n", fr.getArtikel().toString());
    }

    return (bon + "Factuur id: " +id + "\n Datum: " + datum + "\n Korting: " + korting + "\n Totaalbedrag: " + totaal);
}


}
