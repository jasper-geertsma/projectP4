public class Persoon {


    private Datum d;

    private int BSN;
    private String voornaam;
    private String achternaam;
    private String geboortedatum;
    private char geslacht;
    /*
     * Constructor
     *
     */
    public Persoon(int bsn, String voornaam, String achternaam, int dag, int maand, int jaar , String geslacht) {
        d = new Datum(dag, maand, jaar);

        BSN = bsn;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.geboortedatum = d.getDatumAsString();
        this.geslacht = geslacht;
    }

    public Persoon(){
        BSN = 0;
        voornaam = "Onbekend";
        achternaam = "Onbekend";
        geboortedatum = "Onbekend";
    }

    /*
    * Check voor geslacht
    * */
    public boolean checkGeslacht(String check){
        if(check == "Man"){
            geslacht = M;
        }
        return false;
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
}
