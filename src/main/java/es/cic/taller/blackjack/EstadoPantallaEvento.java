package es.cic.taller.blackjack;

import java.util.List;

import es.cic.taller.blackjack.Carta;
import es.cic.taller.blackjack.Mano;
import es.cic.taller.blackjack.TipoEventoPartida;

public class EstadoPantallaEvento extends EstadoPantallaSimple {
	private String suerteActual;

	private EstadoPantallaSimple enfrente;
	private EstadoPantallaSimple izquierda;
	private EstadoPantallaSimple derecha;

	public EstadoPantallaEvento(TipoEventoPartida eventoPartida, String suerteActual, String nombre, String mensaje, List<Carta> listaCartas, int apuestaMayor,
			int apuestaMenor, int apuestaPares, int apuestaJuego, int apuestaActual, boolean aceptar, boolean pasar, boolean mus,
			boolean noHayMus, boolean descartar, boolean apostar, int cuantoApuesta, boolean envidar, EstadoPantallaSimple enfrente,
			EstadoPantallaSimple izquierda, EstadoPantallaSimple derecha, boolean tengoPares, boolean tengoJuego, 
			boolean manoInicial, boolean manoHabla, Mano mano) {
		super(eventoPartida, nombre, mensaje, listaCartas, apuestaMayor, apuestaMenor, apuestaPares, apuestaJuego,
				apuestaActual, aceptar, pasar, mus, noHayMus, descartar, apostar, cuantoApuesta, envidar, tengoPares, tengoJuego, manoInicial, manoHabla, mano);
		this.suerteActual = suerteActual;

		this.enfrente = enfrente;
		this.izquierda = izquierda;
		this.derecha = derecha;
	}

	

	public String getSuerteActual() {
		return suerteActual;
	}

	public void setSuerteActual(String suerteActual) {
		this.suerteActual = suerteActual;
	}
	
	public EstadoPantallaSimple getEnfrente() {
		return enfrente;
	}

	public void setEnfrente(EstadoPantallaSimple enfrente) {
		this.enfrente = enfrente;
	}

	public EstadoPantallaSimple getIzquierda() {
		return izquierda;
	}

	public void setIzquierda(EstadoPantallaSimple izquierda) {
		this.izquierda = izquierda;
	}

	public EstadoPantallaSimple getDerecha() {
		return derecha;
	}

	public void setDerecha(EstadoPantallaSimple derecha) {
		this.derecha = derecha;
	}

}
