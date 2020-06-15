public class Artikel {
    private String naam;
    private double prijs;
    private int korting;

    public Artikel(String naam, double prijs) {
        this.naam = naam;
        this.prijs = prijs;
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

    public int getKorting() {return korting; }
    /*
    * De getters
     */
    public String getNaam() {
        return naam;
    }

    public double getPrijs() {
        return prijs;
    }

    public void setKorting(int korting) {this.korting = korting;}
}
