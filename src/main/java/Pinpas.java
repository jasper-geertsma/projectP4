public class Pinpas extends Betaalwijze {

    private double kredietlimiet;

    /**
     * Methode om kredietlimiet te zetten
     *
     * @param kredietlimiet
     */
    public void setKredietLimiet(double kredietlimiet) {
        // method body omitted
        this.kredietlimiet = kredietlimiet;
    }

    /**
     * Methode om betaling af te handelen
     */
    public void betaal(double tebetalen) throws TeWeinigGeldException {
        // method body omitted KREDIET LIMIET FIX
        if (saldo >= tebetalen){
            setSaldo(saldo - tebetalen);
        }
        else {
            throw new TeWeinigGeldException("je hebt te weinig geld");
        }
    }

}
