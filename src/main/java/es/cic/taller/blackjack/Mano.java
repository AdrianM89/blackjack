package es.cic.taller.blackjack;

public class Mano {
	
	public static int PUNTUACION_LIMITE = 21;
	
	private Carta carta1;
	private Carta carta2;
	
	
	public Carta getCarta1() {
		return carta1;
	}
	public void setCarta1(Carta carta1) {
		this.carta1 = carta1;
	}
	public Carta getCarta2() {
		return carta2;
	}
	public void setCarta2(Carta carta2) {
		this.carta2 = carta2;
	}
	
	
	public int getPuntuacion() {
		return 
				getCarta1().getNumero().getValor() +
				getCarta2().getNumero().getValor();
	
	}
	
	public boolean sigueJugando() {
		
		return getPuntuacion() < PUNTUACION_LIMITE; //Devuelve "true" si la puntuación es menor que 21
	}
	
	
	
}
