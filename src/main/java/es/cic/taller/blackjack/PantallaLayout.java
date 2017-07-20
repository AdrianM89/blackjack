package es.cic.taller.blackjack;

import com.vaadin.server.Page;
import com.vaadin.server.Page.Styles;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextField;

public class PantallaLayout extends GridLayout {
	
	private TapeteForm tapeteFormJugador;
	private TapeteForm tapeteFormDealer;
	TextField apuesta = new TextField();
	private Jugador jugador1;
	private Dealer dealer;
	
	
	private MyUI myUI;
	
	private Baraja baraja;
	
	public PantallaLayout (MyUI myUI, Baraja baraja) {
		
		final Styles styles = Page.getCurrent().getStyles();
		
		styles.add(".v-gridlayout {background-color: green}");
		
		jugador1 = new Jugador("Jugador1", 500);
		

		
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
		
		botonApostar.setEnabled(true);
		botonApostar.addClickListener(e -> 	apuesta.setVisible(true));
        apuesta.setPlaceholder("$2 - $500");
        apuesta.setMaxLength(3);
        updateCaption(0);
        Button intro = new Button("Intro");
        intro.setVisible(false);
        apuesta.addValueChangeListener(event -> {
        	updateCaption(event.getValue().length());
            jugador1.apostar(Integer.parseInt(apuesta.getValue()));
            intro.setVisible(true);
            intro.addClickListener(e -> 	{
            	apuesta.setVisible(false);
            	intro.setVisible(false);
            });
        });
		
		
		
		HorizontalLayout horizontalLayout = new HorizontalLayout();
		
		apuesta.setVisible(false);
		horizontalLayout.addComponents(botonApostar, botonRetirar, botonDameCarta, botonMePlanto, botonSeparar, apuesta, intro);
		
		
		
		
		
		setRows(3);
		setColumns(1);
		
		addComponent(tapeteFormJugador, 0, 1);
		addComponent(tapeteFormDealer, 0, 0);
		addComponent(horizontalLayout, 0, 2);
		
				
	}
	
    private void updateCaption(final int textLength) {
        final StringBuilder builder = new StringBuilder();
        builder.append(String.valueOf(textLength));
        if (apuesta.getMaxLength() >= 0) {
            builder.append("/").append(apuesta.getMaxLength());
        }
        builder.append(" characters");
        apuesta.setCaption(builder.toString());
    }

}
