package es.cic.taller.blackjack;


import java.lang.reflect.Field;
import java.util.ArrayList;


import java.util.List;


import com.vaadin.server.Page;
import com.vaadin.server.Page.Styles;

import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;

import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

import com.vaadin.ui.Label;

import com.vaadin.ui.TextField;

public class PantallaLayout extends GridLayout {
	
	final static int PUNTUACION_21 = 21;
	final static int PUNTUACION_17 = 17;
	
	private int resultado;
	private int resultadoJugador;

	private TapeteForm tapeteFormJugador;
	private TapeteForm tapeteFormDealer;

	private TapeteForm tapeteFormNuevaCarta;
	private TapeteForm tapeteFormNuevaCarta2;
	private TapeteForm tapeteFormNuevaCartaDealer;

	private HorizontalLayout horizontalLayoutSeparar;
	private TapeteForm tapeteFormJugadorNuevo;

	private TextField apuesta = new TextField();

	private Jugador jugador1;
	private Dealer dealer;
	private ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
	private ArrayList<Jugador> jugadoresYaPlantados = new ArrayList<Jugador>();



	private MyUI myUI;
	
	private Baraja baraja;
	HorizontalLayout horizontalLayout = new HorizontalLayout();

	public PantallaLayout(MyUI myUI, Baraja baraja) {

		final Styles styles = Page.getCurrent().getStyles();
		styles.add(".v-gridlayout {background-color: green}");

		jugador1 = new Jugador("Jugador1", 500);
		
		jugadores.add(jugador1);

		jugadores.add(jugador1);

		this.myUI = myUI;

		this.baraja = baraja;

		
		Label puntuacion = new Label();
		puntuacion.addStyleName("h2");

		horizontalLayoutSeparar = new HorizontalLayout();

		Mano manoDealer = baraja.getManoDealer();
		tapeteFormDealer = new TapeteForm(myUI);
		tapeteFormDealer.setMano(manoDealer);

		
		Mano manoJugador = baraja.getManoJugador();
		tapeteFormJugador = new TapeteForm(myUI);
		tapeteFormJugador.setMano(manoJugador);


		Button botonDameCarta = new Button("Nueva carta");
		Button botonDameCartaSegundaMano = new Button("Nueva carta");
		botonDameCartaSegundaMano.setVisible(false);

		Button botonMePlanto = new Button("Plantarse");
		Button botonMePlantoSegundaMano = new Button("Plantarse");
		botonMePlantoSegundaMano.setVisible(false);

		Button botonSeparar = new Button("Separar");
		botonSeparar.setEnabled(false);

		Button botonApostar = new Button("Apostar");

		Button botonRetirar = new Button("Retirarse");

		
		
		// APOSTAR

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


		botonRetirar.addClickListener(e -> {

			botonDameCarta.setEnabled(false);
			botonApostar.setEnabled(false);
			botonMePlanto.setEnabled(false);
			botonSeparar.setEnabled(false);
			botonDameCartaSegundaMano.setEnabled(false);
			botonMePlantoSegundaMano.setEnabled(false);
		});


		horizontalLayout.addComponents(botonApostar, botonRetirar, botonDameCarta, botonDameCartaSegundaMano, botonMePlanto, 
				botonMePlantoSegundaMano, botonSeparar, apuesta, intro);

		// BOTON DAME CARTA
		botonDameCarta.addClickListener(e -> {
			tapeteFormNuevaCarta = new TapeteForm(myUI);
			tapeteFormNuevaCarta.setNuevaCarta(baraja.getNuevaCarta());
			tapeteFormJugador.addComponent(tapeteFormNuevaCarta);			
			manoJugador.anhadirCarta(tapeteFormNuevaCarta.cartaNueva);
			puntuacion.setValue("Puntuacion: "+manoJugador.getPuntuacion());

		});

				// ME PLANTO
		botonMePlanto.addClickListener(e -> {

			mePlanto(myUI, baraja, manoDealer, botonDameCarta, botonDameCartaSegundaMano, botonMePlanto,
					botonMePlantoSegundaMano);
		});
		
		
		
		
		//Funcionalidad para separar mano cuando son las cartas iguales

		// SEPARAR

		if (manoJugador.getCarta(1).getNumero() == manoJugador.getCarta(2).getNumero()) {
			botonSeparar.setEnabled(true);
			botonSeparar.addClickListener(e -> {

				botonSeparar.setEnabled(false);
				addComponent(horizontalLayoutSeparar, 1,1);

				tapeteFormJugador.setMano(baraja.getManoSeparada1(manoJugador));
				tapeteFormJugadorNuevo = new TapeteForm(myUI);
				tapeteFormJugadorNuevo.setMano(baraja.getManoSeparada2(manoJugador));
				horizontalLayoutSeparar.addComponent(tapeteFormJugadorNuevo);
			});
		}

		
		puntuacion.setValue("Puntuacion: " +manoJugador.getPuntuacion());
		
		setRows(5);
		setColumns(2);

		addComponent(tapeteFormJugador, 0, 1);
		addComponent(tapeteFormDealer, 0, 0);
		addComponent(horizontalLayout, 0, 2);
		addComponent(puntuacion,0,4);
	}




	
	
	private void mePlanto(MyUI myUI, Baraja baraja, Mano manoDealer, Button botonDameCarta,
			Button botonDameCartaSegundaMano, Button botonMePlanto, Button botonMePlantoSegundaMano) {

		if (horizontalLayoutSeparar == null) {
			botonDameCarta.setVisible(!botonDameCarta.isVisible());
			jugadoresYaPlantados.add(jugador1);
			if (jugadoresYaPlantados.size() == jugadores.size()) {
				int puntuacionDealer = manoDealer.getCarta(1).getNumero().getValor()
						+ manoDealer.getCarta(2).getNumero().getValor();
				if (puntuacionDealer < PUNTUACION_17) {
					tapeteFormNuevaCartaDealer = new TapeteForm(myUI);
					tapeteFormNuevaCartaDealer.setNuevaCarta(baraja.getNuevaCarta());
					tapeteFormDealer.addComponent(tapeteFormNuevaCartaDealer);
				} else {
					for (int i = 0; i < jugadores.size(); i++) {
						if (puntuacionDealer < PUNTUACION_21) {
							if (puntuacionDealer > jugadores.get(i).puntuacion()) {
								jugadores.get(i)
										.setDinero(jugadores.get(i).getDinero() - jugadores.get(i).getApuesta());
								jugadores.get(i).apostar(0);
							} else if (puntuacionDealer == jugadores.get(i).puntuacion()) {
								jugadores.get(i).apostar(0);
							} else if (puntuacionDealer < jugadores.get(i).puntuacion()) {
								jugadores.get(i)
										.setDinero(jugadores.get(i).getDinero() + jugadores.get(i).getApuesta());
								jugadores.get(i).apostar(0);
							}
						} else if (puntuacionDealer == PUNTUACION_21) {
							if (jugadores.get(i).puntuacion() == PUNTUACION_21) {
								jugadores.get(i).apostar(0);
							} else {
								jugadores.get(i)
										.setDinero(jugadores.get(i).getDinero() - jugadores.get(i).getApuesta());
								jugadores.get(i).apostar(0);
							}
						} else {
							if (jugadores.get(i).puntuacion() < PUNTUACION_21) {
								jugadores.get(i)
										.setDinero(jugadores.get(i).getDinero() + jugadores.get(i).getApuesta());
								jugadores.get(i).apostar(0);
							} else if (jugadores.get(i).puntuacion() == PUNTUACION_21) {
								jugadores.get(i)
										.setDinero(jugadores.get(i).getDinero() + jugadores.get(i).getApuesta() * 1.5);
								jugadores.get(i).apostar(0);
							} else {
								jugadores.get(i)
										.setDinero(jugadores.get(i).getDinero() - jugadores.get(i).getApuesta());
								jugadores.get(i).apostar(0);
							}
						}
					}
				}
			}
		} else {
			botonDameCarta.setVisible(false);
			botonMePlanto.setVisible(false);
			botonDameCartaSegundaMano.setVisible(true);
			botonMePlantoSegundaMano.setVisible(true);
			botonDameCartaSegundaMano.addClickListener(ev -> {

				tapeteFormNuevaCarta2 = new TapeteForm(myUI);
				tapeteFormNuevaCarta2.setNuevaCarta(baraja.getNuevaCarta());
				
				tapeteFormJugadorNuevo.addComponent(tapeteFormNuevaCarta2);
			});
			botonMePlantoSegundaMano.addClickListener(eve -> {

				String str = new String();
				botonDameCartaSegundaMano.setVisible(false);
				jugadoresYaPlantados.add(jugador1);
				if (jugadoresYaPlantados.size() == jugadores.size()) {
					int puntuacionDealer = manoDealer.getCarta(1).getNumero().getValor()
							+ manoDealer.getCarta(2).getNumero().getValor();
					if (puntuacionDealer < PUNTUACION_17) {
						tapeteFormNuevaCartaDealer = new TapeteForm(myUI);
						tapeteFormNuevaCartaDealer.setNuevaCarta(baraja.getNuevaCarta());
						tapeteFormDealer.addComponent(tapeteFormNuevaCartaDealer);
					} else {
						for (int i = 0; i < jugadores.size(); i++) {
							if (puntuacionDealer < PUNTUACION_21) {
								if (puntuacionDealer > jugadores.get(i).puntuacion()) {
									jugadores.get(i)
											.setDinero(jugadores.get(i).getDinero() - jugadores.get(i).getApuesta());
									jugadores.get(i).apostar(0);
									str = "Ganador: Banca";
								} else if (puntuacionDealer == jugadores.get(i).puntuacion()) {
									jugadores.get(i).apostar(0);
									str = "Empate";
								} else if (puntuacionDealer < jugadores.get(i).puntuacion()) {
									jugadores.get(i)
											.setDinero(jugadores.get(i).getDinero() + jugadores.get(i).getApuesta());
									jugadores.get(i).apostar(0);
									str = "Ganador: " + jugadores.get(i).getNombre();
								}
							} else if (puntuacionDealer == PUNTUACION_21) {
								if (jugadores.get(i).puntuacion() == PUNTUACION_21) {
									jugadores.get(i).apostar(0);
									str = "Empate";
								} else {
									jugadores.get(i)
											.setDinero(jugadores.get(i).getDinero() - jugadores.get(i).getApuesta());
									jugadores.get(i).apostar(0);
									str = "Ganador: Banca";
								}
							} else {
								if (jugadores.get(i).puntuacion() < PUNTUACION_21) {
									jugadores.get(i)
											.setDinero(jugadores.get(i).getDinero() + jugadores.get(i).getApuesta());
									jugadores.get(i).apostar(0);
									str = "Ganador: " + jugadores.get(i).getNombre();
								} else if (jugadores.get(i).puntuacion() == PUNTUACION_21) {
									jugadores.get(i).setDinero(
											jugadores.get(i).getDinero() + jugadores.get(i).getApuesta() * 1.5);
									jugadores.get(i).apostar(0);
									str = "Ganador: " + jugadores.get(i).getNombre();
								} else {
									jugadores.get(i)
											.setDinero(jugadores.get(i).getDinero() - jugadores.get(i).getApuesta());
									jugadores.get(i).apostar(0);
									str = "Empate";
								}
							}
						}
					}
				}
				Label ganador = new Label(str);
				addComponent(ganador, 0, 5);
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