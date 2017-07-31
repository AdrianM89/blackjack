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

		for (int i = 1; i <= Palo.CUANTAS_CARTA_POR_PALO; i++) {
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

	public Carta getCarta() {

		Carta carta = listaCartas.get(0);
		barajear();
		return carta;
	}

	public Mano getManoJugador() {
		Mano mano = new Mano();
		Carta carta1 = getCarta();
		mano.anhadirCarta(carta1);
		mano.setCarta(1, carta1);
		Carta carta2 = getCarta();
		mano.anhadirCarta(carta2);
		mano.setCarta(2, carta2);

		return mano;

	}

	public Mano getManoSeparada1(Mano mano) {

		Mano manoNueva = new Mano();
		manoNueva.anhadirCarta(mano.getCarta(1));
		manoNueva.anhadirCarta(getCarta());
		return manoNueva;

	}

	public Mano getManoSeparada2(Mano mano) {

		Mano manoNueva = new Mano();
		manoNueva.anhadirCarta(mano.getCarta(1));
		manoNueva.anhadirCarta(getCarta());
		return manoNueva;

	}

	public Mano getManoDealer() {
		Mano manoDealer = new Mano();
		Carta carta1 = getCarta();
		manoDealer.anhadirCartaDealer(carta1);
		Carta dorso = Carta.getDorso();
		manoDealer.anhadirCartaDealer(dorso);
		manoDealer.setCartaDealer(1, carta1);
		manoDealer.setCartaDealer(2, dorso);

		return manoDealer;

	}

	public Mano getNuevaCarta() {
		Mano mano = new Mano();
		Carta carta = getCarta();
		mano.anhadirCarta(carta);

		return mano;

	}

	public Mano getNuevaCartaDealer() {
		Mano manoDealer = new Mano();
		Carta carta = getCarta();
		manoDealer.anhadirCartaDealer(carta);

		return manoDealer;

	}

}