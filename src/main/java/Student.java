
/**
 * class Student - geef hier een beschrijving van deze class
 *
 * @author (jouw naam)
 * @version (versie nummer of datum)
 */
public class Student extends Persoon
{
    // instance variables - vervang deze door jouw variabelen
    private final int studentNummer;
    private final String studieRichting;

    /**
     * Constructor voor objects van class Student
     */
     public Student(int bsn, String voornaam, String achternaam, int dag, int maand, int jaar , char geslachtkeuze,int studentNummer, String studieRichting)  {
         super(bsn, voornaam, achternaam, dag, maand, jaar, geslachtkeuze);
         this.studentNummer = studentNummer;
         this.studieRichting = studieRichting;
 
    }
}
