package es.cic.taller.blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Baraja {
	private List<Carta> listaCartas = new ArrayList<>();
	
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
	
	private List<Carta> getPalo(Palo palo) {
		
		List<Carta> listaCartasPalo = new ArrayList<>();
		
		for (int i= 1; i <= 10; i++) {
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
		
		generarCartas();
	}
	
	private Carta getCarta() {
		Carta carta = listaCartas.remove(0);
		return carta;		
	}
	
	public Tapete getTapete() {
		Tapete tapete = new Tapete();
		tapete.setCarta1(getCarta());
		tapete.setCarta2(getCarta());
	
		return tapete;
	}
}
