
/**
 * class TeWeinigGeldException - geef hier een beschrijving van deze class
 *
 * @author (jouw naam)
 * @version (versie nummer of datum)
 */
public class TeWeinigGeldException extends Exception
{

    /**
     * Constructor voor objects van class TeWeinigGeldException
     */
    public TeWeinigGeldException()
    {
        super("Er is te weinig geld");
    }
    
    public TeWeinigGeldException(Exception e)
    {
        super(e);
    }
    
    public TeWeinigGeldException(String foutmelding)
    {
        super(foutmelding);
    }

}
