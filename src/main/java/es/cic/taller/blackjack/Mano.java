
package es.cic.taller.blackjack;

import java.util.ArrayList;

public class Mano {
	
	public static int PUNTUACION_LIMITE = 21;
	
	ArrayList<Carta> cartas = new ArrayList<Carta>();
	
	
	public int getPuntuacion() {
		int puntuacion = 0;
		for(int i = 0; i<cartas.size(); i++) {
			puntuacion += cartas.get(i).getNumero().getValor();
		}
		return puntuacion;
	}

	
	public Carta getCarta(int i) {
		if(i > cartas.size()) {
			return null;
		}
		return cartas.get(i-1);
	}
	
	public void setCarta(int i, Carta carta) {
		for(int j = 0; j<cartas.size(); j++) {
			if(j == (i-1)) {
			cartas.set(i-1, carta);
			}
		}
	}
	
	public void anhadirCarta(Carta carta) {
		 cartas.add(carta);
	}
	
	public boolean sigueJugando() {
		
		return getPuntuacion() < PUNTUACION_LIMITE; //Devuelve "true" si la puntuación es menor que 21
	}
	
	
	
}


