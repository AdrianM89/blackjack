package es.cic.taller.blackjack;

public class Dealer {
	
	private Mano manoActual;
	private double banca;
	
	public Dealer() {
		banca = 1.5*500*3*2;
	}
	public Mano getManoActual() {
		return manoActual;
	}
	public void setManoActual(Mano manoActual) {
		this.manoActual = manoActual;
	}
	public double getBanca() {
		return banca;
	}
	public double setBanca(double dinero) {
		banca = dinero;
		return banca;
	}
}