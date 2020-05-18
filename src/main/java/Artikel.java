public class Artikel {
    private String naam;
    private int prijs;

    public Artikel() {
        this.naam = naam;
        this.prijs = prijs;
    }

    /*
    * Opgave 1
    * De setters
    */
    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setPrijs(int prijs){
        this.prijs = prijs;
    }
    /*
    * De getters
     */
    public String getNaam() {
        return naam;
    }

    public int getPrijs() {
        return prijs;
    }
}
