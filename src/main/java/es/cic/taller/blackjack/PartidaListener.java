package es.cic.taller.blackjack;

import es.cic.taller.blackjack.EstadoPantallaEvento;

public interface PartidaListener {
	String getNombreJugadorActual();
	void onPartidaEvent(EstadoPantallaEvento estadoPantallaEvento);
	
}
