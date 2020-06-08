public class Persoon {

    private Datum d;

    private int BSN;
    private String voornaam;
    private String achternaam;
    private String geboortedatum;
    private String geslacht;
    private Betaalwijze betaalwijze;
    /*
     * Constructor
     *
     */
    public Persoon(int bsn, String voornaam, String achternaam, int dag, int maand, int jaar , char geslachtkeuze) {
        d = new Datum(dag, maand, jaar);

        BSN = bsn;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.geboortedatum = d.getDatumAsString();
        checkGeslacht(geslachtkeuze);
    }

    public Persoon(){
        BSN = 0;
        voornaam = "Onbekend";
        achternaam = "Onbekend";
        geboortedatum = "Onbekend";
        geslacht = "Onbekend";
    }

    /*
     * Check voor geslacht
     * */
    public boolean checkGeslacht(char check){
        switch (check){
            case 'm': case 'M' :
                geslacht = "Man";
                return true;

            case 'f': case 'F':
                geslacht = "Vrouw";
                return true;

            default:
                geslacht = "onbekend";
                break;
        }
        return false;
    }

    /*
     * Methode toString
     */
    @Override
    public String toString(){
        String info = "BSN: " + BSN + "; Naam: " + voornaam + " " + achternaam + "; Geslacht: " + geslacht + "; geboortedatum: " + geboortedatum;
        return info;
    }

    /*
     * Getters
     *
     */
    public int getBSN() {
        return BSN;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public String getGeboortedatum() {
        return geboortedatum;
    }

    public String getGeslacht() {
        return geslacht;
    }
    
    public Betaalwijze getBetaalwijze() {
        return betaalwijze;
    }

    /*
     * Setters
     *
     */
    public void setBSN(int BSN) {
        this.BSN = BSN;
    }
    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }
    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }
    public void setGeboortedatum(String geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public void setGeslacht(char check) {
        checkGeslacht(check);
    }
    
    public void setBetaalwijze(Betaalwijze manier){
        this.betaalwijze = manier;
    }
}
