
/**
 * class Docent - geef hier een beschrijving van deze class
 *
 * @author (jouw naam)
 * @version (versie nummer of datum)
 */
public class Docent extends Persoon
{
    // instance variables - vervang deze door jouw variabelen
    String afkorting;
    String afdeling;
    /**
     * Constructor voor objects van class Docent
     */
    
     public Docent(int bsn, String voornaam, String achternaam, int dag, int maand, int jaar , char geslachtkeuze,String afkorting, String afdeling)  {
         super(bsn, voornaam, achternaam, dag, maand, jaar, geslachtkeuze);
         this.afkorting = afkorting;
         this.afdeling = afdeling;
 
    }
}
