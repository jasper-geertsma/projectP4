import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class Dienblad {
    private Stack<Artikel> artikelen;
    private Persoon klant;
    /**
     * Constructor
     */
    public Dienblad(Persoon klant) {
        // method body omitted
        this.klant = klant;
        artikelen = new Stack<>();
    }

    public Dienblad(){
        artikelen = new Stack<>();

    }

    public Persoon getKlant() {
        return klant;
    }

    public void setKlant(Persoon klant) {
        this.klant = klant;
    }
    /**
     * Methode om artikel aan dienblad toe te voegen
     *
     * @param artikel
     */
    public void voegToe(Artikel artikel) {
        artikelen.add(artikel);
    }

    public Iterator<Artikel> getIterator(){
        return artikelen.iterator();
    }

    public Stack<Artikel> getArtikelen (){
        return artikelen;
    }

}

