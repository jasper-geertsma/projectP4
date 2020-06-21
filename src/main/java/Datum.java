public class Datum {

	private int dag;
	private int maand;
	private int jaar;

	/**
	 * Constructor
	 */
	// TODO
	public Datum(int d, int m, int j){
		if(bestaatDatum(d, m, j)) {
			dag = d;
			maand = m;
			jaar = j;
		}
		else {
			new Datum();
		}
	}

	public Datum(){
		dag = 0;
		maand = 0;
		jaar = 0;
	}
	/*
	*	Deze methode controleert of de gegeven datum bestaat
	*
	 */
	public boolean bestaatDatum(int d, int m, int j) {
		// TODO
		int dagen = 0;
		switch (m) {
			case 1:case 3: case 5: case 7:case 8: case 12:
				dagen = 31;
				break;
			case 2:
				if(j % 4 == 0){
					if(j % 100 == 0){
						if (j % 400 == 0){
							dagen = 29;}
						else{
							dagen = 28;}
					}
					else{
						dagen = 29;}
				}
				else{
					dagen = 28;}
				break;
			case 4:case 9:case 11:case 6:
				dagen = 30;
				break;
		}
		if (m >= 1 && m <= 12){

			if (d >= 1 && d <= dagen) {

                return j >= 1900 && j <= 2100;
			}
		}

		return false;
	}

	/**
	 * Getters
	 *
	 * @return Geboortedatum
	 */
	public String getDatumAsString() {
		// TODO
		String datumString = "";

		if(bestaatDatum(dag, maand, jaar)) {
			datumString = dag+"-"+maand+"-"+jaar;
		}
		else {
			new Datum();
			datumString = "Voer aub een goede datum in";
		}

		return datumString;
	}

	public int getDag() {
		return dag;
	}

	public int getMaand() {
		return maand;
	}

	public int getJaar() {
		return jaar;
	}

	/*
	*	Setters
	 */
	public void setDag(int d) {
		dag = d;
	}

	public void setMaand(int m) {
		maand = m;
	}

	public void setJaar(int j) {
		jaar = j;
	}

}
