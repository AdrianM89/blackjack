package es.cic.taller.blackjack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class Baraja {
	private List<Carta> listaCartas = new ArrayList<Carta>();
	
	private List<Carta> listaMonton = new ArrayList<Carta>();
	
	public Baraja() {
		generarCartas();
	}

	private void generarCartas() {
		listaCartas.addAll(getPalo(Palo.CORAZONES));
		listaCartas.addAll(getPalo(Palo.DIAMANTES));
		listaCartas.addAll(getPalo(Palo.PICAS));
		listaCartas.addAll(getPalo(Palo.TREBOLES));
		barajear();
	}
	
	public void tirarAlMonton(Collection<Carta> descartadas) {
		listaMonton.addAll(descartadas);		
	}
	
	private List<Carta> getPalo(Palo palo) {
		
		List<Carta> listaCartasPalo = new ArrayList<Carta>();
		
		for (int i= 1; i <= Palo.CUANTAS_CARTA_POR_PALO; i++) {
			Carta carta = new Carta();
			Numero numero = Numero.getNumero(i);
			carta.setNumero(numero);
			carta.setPalo(palo);
			listaCartasPalo.add(carta);
		}
		return listaCartasPalo;
	}
	
	public void barajear() {
		Collections.shuffle(listaCartas);
	}
	
	public void resetear() {
		listaCartas.clear();
		listaMonton.clear();
		
		generarCartas();
	}
	
	private Carta getCarta() {
		if (listaCartas.isEmpty()) {
			listaCartas.addAll(listaMonton);
			barajear();
			listaMonton.clear();
		}
		Carta carta = listaCartas.remove(0);
		return carta;		
	}
	
	public Collection<Carta> getCartas(int cuantas) {
		Collection<Carta> nuevasCartas = new ArrayList<Carta>();
		for(int i = 0; i < cuantas; i++) {
			nuevasCartas.add(getCarta());
		}
		return nuevasCartas;
	}
}
