package es.cic.taller.blackjack;

public class Apuesta {

	private double cantidad;
	private double apuestaSegura;

	public Apuesta(double cantidad) {
		this.cantidad = cantidad;
	}
	
	public double getCantidad() {
		return cantidad;
	}
	
	public void apuestaSegura() {
		apuestaSegura = cantidad*0.5;
	}

	public double getApuestaSegura() {
		return apuestaSegura;
	}	
}
