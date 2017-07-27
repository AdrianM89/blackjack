package es.cic.taller.blackjack;

import es.cic.taller.blackjack.EstadoPantallaEvento;

public interface PartidaNotifier {
	void firePartidaEvento(EstadoPantallaEvento estadoPartidaEvento);
	void addPartidaListener(PartidaListener partidaListener);
}
