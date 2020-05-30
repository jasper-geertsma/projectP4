
/**
 * class KantineMedewerker - geef hier een beschrijving van deze class
 *
 * @author (jouw naam)
 * @version (versie nummer of datum)
 */
public class KantineMedewerker extends Persoon
{
    private boolean kassa;
    private int medewerkersNummer;
    /*
     * Constructor
     *
     */
    
    /**
     * Constructor voor objects van class KantineMedewerker
     */
    public KantineMedewerker(int bsn, String voornaam, String achternaam, int dag, int maand, int jaar , char geslachtkeuze,int medewerkersNummer, boolean kassa)  {
         super(bsn, voornaam, achternaam, dag, maand, jaar, geslachtkeuze);
         this.kassa = kassa;
         this.medewerkersNummer = medewerkersNummer;
 
    }
    
    }

