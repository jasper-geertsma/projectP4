public class Contant extends Betaalwijze {
    /**
     * Methode om betaling af te handelen
     */
    public void betaal(double tebetalen) throws TeWeinigGeldException {
        // method body omitted
        if (saldo >= tebetalen){
            setSaldo(saldo - tebetalen);
        }
        else {
            throw new TeWeinigGeldException("je hebt te weinig geld");
        }
    }
}
