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
	private TapeteForm tapeteFormNuevaCarta2;
	HorizontalLayout horizontalLayoutSeparar;
	TapeteForm tapeteFormJugadorNuevo;

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
		
		horizontalLayoutSeparar = new HorizontalLayout();
		

		Mano manoJugador = baraja.getManoJugador();
		Mano manoDealer = baraja.getManoDealer();

		tapeteFormJugador = new TapeteForm(myUI);
		tapeteFormJugador.setMano(manoJugador);

		tapeteFormDealer = new TapeteForm(myUI);
		tapeteFormDealer.setMano(manoDealer);

		Button botonDameCarta = new Button("Nueva carta");
		Button botonDameCartaSegundaMano = new Button ("Nueva carta");
		botonDameCartaSegundaMano.setVisible(false);
		Button botonMePlanto = new Button("Plantarse");
		Button botonMePlantoSegundaMano = new Button("Plantarse");
		botonMePlantoSegundaMano.setVisible(false);
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

		horizontalLayout.addComponents(botonApostar, botonRetirar, botonDameCarta, botonDameCartaSegundaMano, botonMePlanto, botonMePlantoSegundaMano, botonSeparar, apuesta,
				intro);

		
		botonDameCarta.addClickListener(e -> {

			tapeteFormNuevaCarta = new TapeteForm(myUI);
			tapeteFormNuevaCarta.setNuevaCarta(baraja.getNuevaCarta());;
			
			//tapeteFormJugador.setNuevaCarta(baraja.getNuevaCarta());
			
			tapeteFormJugador.addComponent(tapeteFormNuevaCarta);

			
			
		});

		
		
		
		// Visible cambio
		botonMePlanto.addClickListener(e -> {
			
			if(horizontalLayoutSeparar==null) {
			botonDameCarta.setVisible(!botonDameCarta.isVisible());
			}
			else {
				botonDameCarta.setVisible(false);
				botonMePlanto.setVisible(false);
				botonDameCartaSegundaMano.setVisible(true);
				botonMePlantoSegundaMano.setVisible(true);
				botonDameCartaSegundaMano.addClickListener(ev -> {

					tapeteFormNuevaCarta2 = new TapeteForm(myUI);
					tapeteFormNuevaCarta2.setNuevaCarta(baraja.getNuevaCarta());;
					//tapeteFormJugador.setNuevaCarta(baraja.getNuevaCarta());
					tapeteFormJugadorNuevo.addComponent(tapeteFormNuevaCarta2);
				});
				botonMePlantoSegundaMano.addClickListener(eve -> botonDameCartaSegundaMano.setVisible(false));
			}
		});

		setRows(3);
		setColumns(2);

		addComponent(tapeteFormJugador, 0, 1);
		addComponent(tapeteFormDealer, 0, 0);
		addComponent(horizontalLayout, 0, 2);
		
		
		//Funcionalidad para separar mano cuando son las cartas iguales
		if (manoJugador.getCarta1().getNumero() == manoJugador.getCarta2().getNumero()) {
			botonSeparar.addClickListener(e -> {
				addComponent(horizontalLayoutSeparar, 1,1);
				tapeteFormJugador.setMano(baraja.getManoSeparada1(manoJugador));
				tapeteFormJugadorNuevo = new TapeteForm(myUI);
				tapeteFormJugadorNuevo.setMano(baraja.getManoSeparada2(manoJugador));
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
