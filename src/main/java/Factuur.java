import javax.persistence.*;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Entity
@Table(name = "factuur")

public class Factuur implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "datum", nullable = false)
    private LocalDate datum;

    @Column(name = "korting", nullable = false)
    private double korting;

    @Column(name = "totaal", nullable = false)
    private double totaal;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "factuur_factuurregel", joinColumns = @JoinColumn(name = "factuur_id"),
            inverseJoinColumns = @JoinColumn(name = "factuurregel_id"))
    private List<FactuurRegel> regels;


    public Factuur() {
        totaal = 0;
        korting = 0;
    }

    public Factuur(Dienblad klant, LocalDate datum) {
        this();
        this.datum = datum;
        regels = new ArrayList<>();
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
    Iterator<Artikel> artikel = klant.getIterator();
    double kortingDagaanbiedingen = 0.0d;
    double totaalPrijsArtikelen = 0.0d;
    Persoon persoon = klant.getKlant();

    System.out.println(datum);

    // Bereken de totaalprijs van de artikelen
    while (artikel.hasNext()) {
        Artikel a = artikel.next();
        totaalPrijsArtikelen += a.getPrijs();
        kortingDagaanbiedingen += a.getKorting();
        // Voeg factuurregel toe
        regels.add(new FactuurRegel(this, a));
    }

    // Check voor korting en bereken de korting
    if (persoon instanceof KortingskaartHouder) {
        KortingskaartHouder kh = (KortingskaartHouder)persoon;
        korting = totaalPrijsArtikelen / 100.0d * kh.geefKortingsPercentage();
        if (kh.heeftMaximum() && korting > kh.geefMaximum()) {
            korting = ((KortingskaartHouder) persoon).geefMaximum();
        }
    }
    korting += Math.round(kortingDagaanbiedingen);
    totaal = Math.round(totaalPrijsArtikelen);
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
