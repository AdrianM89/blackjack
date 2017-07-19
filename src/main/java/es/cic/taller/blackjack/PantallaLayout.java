package es.cic.taller.blackjack;

import com.vaadin.ui.GridLayout;

public class PantallaLayout extends GridLayout {
	
	private TapeteForm tapeteFormJugador;
	private TapeteForm tapeteFormDealer;
	
	
	private MyUI myUI;
	
	private Baraja baraja;
	
	public PantallaLayout (MyUI myUI, Baraja baraja) {
		
		this.myUI = myUI;
		
		this.baraja = baraja;
		
		Mano manoJugador = baraja.getManoJugador();
		Mano manoDealer = baraja.getManoDealer();
		
		tapeteFormJugador = new TapeteForm(myUI);
		tapeteFormJugador.setMano(manoJugador);
		
		tapeteFormDealer = new TapeteForm(myUI);
		tapeteFormDealer.setMano(manoDealer);
		
		
		
		
		
		
		
		
		setRows(2);
		setColumns(1);
		
		addComponent(tapeteFormJugador, 0, 1);
		addComponent(tapeteFormDealer, 0, 0);
				
	}
	

}
