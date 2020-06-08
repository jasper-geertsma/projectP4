
/**
 * class Docent - geef hier een beschrijving van deze class
 *
 * @author (jouw naam)
 * @version (versie nummer of datum)
 */
public class Docent extends Persoon implements KortingskaartHouder
{
    // instance variables - vervang deze door jouw variabelen
    private String afkorting;
    private String afdeling;
    /**
     * Constructor voor objects van class Docent
     */
    
     public Docent(int bsn, String voornaam, String achternaam, int dag, int maand, int jaar , char geslachtkeuze,String afkorting, String afdeling)  {
         super(bsn, voornaam, achternaam, dag, maand, jaar, geslachtkeuze);
         this.afkorting = afkorting;
         this.afdeling = afdeling;
 
    }
    
    /*
     * Setters
     */
    public void setAfdeling(String afdeling){
        this.afdeling = afdeling;
    }
    
    public void setAfkorting(String afkorting){
        this.afkorting = afkorting;
    }
    
    /*
     * Getters
     */
    public String getAfdeling(){
        return afdeling;
    }
    
    public String getAfkorting(){
        return afkorting;
    }
    
    public double geefKortingsPercentage() {
        return 25.0;
    }


    public boolean heeftMaximum() {
        return true;
    }

    public double geefMaximum() {
        return 1.0;
    }    
}
