package es.cic.taller.blackjack;

import com.vaadin.server.Page;
import com.vaadin.server.Page.Styles;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;

public class PantallaLayout extends GridLayout {
	
	private TapeteForm tapeteFormJugador;
	private TapeteForm tapeteFormDealer;
	
	
	private MyUI myUI;
	
	private Baraja baraja;
	
	public PantallaLayout (MyUI myUI, Baraja baraja) {
		
		final Styles styles = Page.getCurrent().getStyles();
		
		styles.add(".v-gridlayout {background-color: green}");
		

		
		this.myUI = myUI;
		
		this.baraja = baraja;
		
		Mano manoJugador = baraja.getManoJugador();
		Mano manoDealer = baraja.getManoDealer();
		
		tapeteFormJugador = new TapeteForm(myUI);
		tapeteFormJugador.setMano(manoJugador);
		
		tapeteFormDealer = new TapeteForm(myUI);
		tapeteFormDealer.setMano(manoDealer);
		
		
		Button botonDameCarta = new Button("Nueva carta");
		Button botonMePlanto = new Button("Plantarse");
		Button botonSeparar = new Button("Separar");
		Button botonApostar = new Button("Apostar");
		Button botonRetirar = new Button("Retirarse");
		
		
		botonDameCarta.addClickListener(e -> {
			
			
		});
		
		
		
		HorizontalLayout horizontalLayout = new HorizontalLayout();
		
		horizontalLayout.addComponents(botonApostar, botonRetirar, botonDameCarta, botonMePlanto, botonSeparar);
		
		
		
		
		
		setRows(3);
		setColumns(1);
		
		addComponent(tapeteFormJugador, 0, 1);
		addComponent(tapeteFormDealer, 0, 0);
		addComponent(horizontalLayout, 0, 2);
		
				
	}
	

}
