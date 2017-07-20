package es.cic.taller.blackjack;

import com.vaadin.server.Page;
import com.vaadin.server.Page.Styles;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextField;

public class PantallaLayout extends GridLayout {

	private TapeteForm tapeteFormJugador;
	private TapeteForm tapeteFormDealer;

	private TapeteForm tapeteFormNuevaCarta;

	TextField apuesta = new TextField();
	private Jugador jugador1;
	private Dealer dealer;

	private MyUI myUI;

	private Baraja baraja;

	public PantallaLayout(MyUI myUI, Baraja baraja) {

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

		apuesta.setVisible(false);
		botonApostar.setEnabled(true);
		botonApostar.addClickListener(e -> apuesta.setVisible(true));
		apuesta.setPlaceholder("$2 - $999");
		apuesta.setMaxLength(3);
		updateCaption(0);
		Button intro = new Button("Intro");
		intro.setVisible(false);
		apuesta.addValueChangeListener(event -> {
			updateCaption(event.getValue().length());
			jugador1.apostar(Integer.parseInt(apuesta.getValue()));
			intro.setVisible(true);
			intro.addClickListener(e -> {
				apuesta.setVisible(false);
				intro.setVisible(false);
			});
		});

		
		
		HorizontalLayout horizontalLayout = new HorizontalLayout();

		horizontalLayout.addComponents(botonApostar, botonRetirar, botonDameCarta, botonMePlanto, botonSeparar, apuesta,
				intro);

		
		botonDameCarta.addClickListener(e -> {

			tapeteFormNuevaCarta = new TapeteForm(myUI);
			tapeteFormNuevaCarta.setNuevaCarta(baraja.getNuevaCarta());;
			
			//tapeteFormJugador.setNuevaCarta(baraja.getNuevaCarta());
			
			tapeteFormJugador.addComponent(tapeteFormNuevaCarta);
			
			
		});

		
		
		
		// Visible cambio
		botonMePlanto.addClickListener(e -> {

			botonDameCarta.setVisible(!botonDameCarta.isVisible());
		});

		setRows(3);
		setColumns(2);

		addComponent(tapeteFormJugador, 0, 1);
		addComponent(tapeteFormDealer, 0, 0);
		addComponent(horizontalLayout, 0, 2);
		
		if (manoJugador.getCarta1().getNumero() == manoJugador.getCarta2().getNumero()) {
			botonSeparar.addClickListener(e -> {
				HorizontalLayout horizontalLayoutSeparar = new HorizontalLayout();
				addComponent(horizontalLayoutSeparar, 1,1);
				tapeteFormJugador.setMano(baraja.getManoSeparada(manoJugador));
				TapeteForm tapeteFormJugadorNuevo = new TapeteForm(myUI);
				tapeteFormJugadorNuevo.setMano(baraja.getManoSeparada(manoJugador));
				horizontalLayoutSeparar.addComponent(tapeteFormJugadorNuevo);
			});
		}

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
