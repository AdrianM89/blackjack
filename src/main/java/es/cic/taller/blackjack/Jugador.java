package es.cic.taller.blackjack;

public class Jugador {
	
	private String nombre;
	private Mano manoActual;
	private double dinero;
	private int apuesta;
	
	public Jugador(String nombre, double dinero) {
		this.nombre = nombre;
		this.dinero = dinero;
	}
	public Mano getManoActual() {
		return manoActual;
	}
	public void setManoActual(Mano manoActual) {
		this.manoActual = manoActual;
	}
	public String getNombre() {
		return nombre;
	}
	public double getDinero() {
		return dinero;
	}
	public double setDinero(double dinero) {
		this.dinero = dinero;
		return dinero;
	}
	public int getApuesta() {
		return apuesta;
	}
	public void apostar(int apuesta) {
		this.apuesta = apuesta;
	}
}
