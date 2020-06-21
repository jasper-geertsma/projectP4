import javax.persistence.*;
import java.io.Serializable;

@Entity
public class FactuurRegel implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private Factuur factuur;

    @Embedded
    private Artikel artikel;

    public FactuurRegel() {

    }

    public FactuurRegel(Factuur factuur, Artikel artikel) {
        this.factuur = factuur;
        this.artikel = artikel;
    }

    public Artikel getArtikel() {
        return artikel;
    }

    public Factuur getFactuur() {
        return factuur;
    }

}
