public class Artikel {
    private String naam;
    private double prijs;

    public Artikel(String naam, double prijs) {
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

    public void setPrijs(double prijs){
        this.prijs = prijs;
    }
    /*
    * De getters
     */
    public String getNaam() {
        return naam;
    }

    public double getPrijs() {
        return prijs;
    }
}
