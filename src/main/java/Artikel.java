public class Artikel {
    private String naam;
    private double prijs;
    private double korting;

    public Artikel(String naam, double prijs, double korting) {
        this.naam = naam;
        this.prijs = prijs;
        this.korting = korting;
    }

    public Artikel(String naam, double prijs) {
        this.naam = naam;
        this.prijs = prijs;
        korting = 0.0d;
    }

    public Artikel(){
        this.naam = null;
        this.prijs = 0.0;
        this.korting = 0;
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

    public double getKorting() {return korting; }
    /*
    * De getters
     */
    public String getNaam() {
        return naam;
    }

    public double getPrijs() {
        return prijs;
    }

    public void setKorting(double korting) {this.korting = korting;}

    @Override
    public String toString() {
        return String.format("Naam: %s, prijs: %f", naam, prijs);
    }
}
