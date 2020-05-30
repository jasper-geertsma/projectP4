public class Administratie {    
    
    private static final int DAYS_IN_WEEK = 7;
    /**
     * Deze methode berekent van de int array aantal de gemiddelde waarde
     *
     * @param aantal
     * @return het gemiddelde
     */
    private Administratie() {
    }
    
    public static double berekenGemiddeldAantal(int[] aantal) {
        if(aantal.length == 0) {
            return 0;
        }
        double gemiddelde = 0;
        for(int totaal: aantal) {
            gemiddelde += totaal;
        }
        if(gemiddelde == 0) {
            return 0;
        }
        gemiddelde = gemiddelde / aantal.length;
        return gemiddelde;
    }

    /**
     * Deze methode berekent van de double array omzet de gemiddelde waarde
     *
     * @param omzet
     * @return het gemiddelde
     */
    public static double berekenGemiddeldeOmzet(double[] omzet) {
        if(omzet.length == 0) {
            return 0;
        } 
        double gemiddelde = 0;
        for(double totaal: omzet) {
            gemiddelde =+ totaal;
        }
        if(gemiddelde == 0) {
            return 0;
        }
        return gemiddelde /= omzet.length;
    }

    /**
     * Methode om dagomzet uit te rekenen
     *
     * @param omzet
     * @return array (7 elementen) met dagomzetten
     */

    public static double[] berekenDagOmzet(double[] omzet) {
        double[] temp = new double[DAYS_IN_WEEK];
        for(int i = 0; i < DAYS_IN_WEEK; i++) {
            int j = 0;
            while(omzet.length > i + DAYS_IN_WEEK * j) {
                temp[i] += omzet[i + DAYS_IN_WEEK * j];
                j++;
           }
        }
        return temp;
    }
}
