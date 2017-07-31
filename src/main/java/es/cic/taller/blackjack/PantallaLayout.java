package es.cic.taller.blackjack;


import java.io.File;
import java.util.ArrayList;


import java.util.List;

import org.atmosphere.config.service.Delete;

import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinService;
import com.vaadin.server.Page.Styles;

import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;

import com.vaadin.ui.Image;
import com.vaadin.ui.Label;

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

	private List<Carta> nuevasCartas = new ArrayList<>();

	private MyUI myUI;
	private Mano manoJugador;
	private Mano manoDealer;
	
	private Baraja baraja;
	HorizontalLayout horizontalLayout = new HorizontalLayout();
		

	public PantallaLayout(MyUI myUI, Baraja baraja) {

		final Styles styles = Page.getCurrent().getStyles();
		styles.add(".v-gridlayout {background-color: green}");

		jugador1 = new Jugador("Jugador1", 500);
		
		jugadores.add(jugador1);

		this.myUI = myUI;

		this.baraja = baraja;

		
		Label puntuacion = new Label();
		Label puntuacionDealerLabel = new Label();
		puntuacion.addStyleName("h2");
		
		int dineroInicial = 1000;
		Label dinero = new Label();
		dinero.addStyleName("h2");
		dinero.setValue("Dinero: " + dineroInicial);
		puntuacionDealerLabel.addStyleName("h2");

		manoDealer = baraja.getManoDealer();
		tapeteFormDealer = new TapeteForm(myUI);
		tapeteFormDealer.setManoDealer(manoDealer);

		manoJugador = baraja.getManoJugador();
		tapeteFormJugador = new TapeteForm(myUI);
		tapeteFormJugador.setMano(manoJugador);

		Button botonComenzar = new Button("Comenzar");
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
		
		Button botonNuevoJuego = new Button("Nuevo Juego");
		botonNuevoJuego.setEnabled(false);

		//Imagen de inicio
		String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
		FileResource resource = new FileResource(new File(basepath + "/images/blackjack.png"));
		Image imagen = new Image(null, resource);

		// APOSTAR
		apuesta.setVisible(false);
		botonApostar.setEnabled(true);
		botonApostar.addClickListener(e -> apuesta.setVisible(true));
		apuesta.setPlaceholder("$1 - $999");
		apuesta.setMaxLength(3);

		apuesta.addValueChangeListener(event -> {
			jugador1.apostar(Integer.parseInt(apuesta.getValue()));
			botonComenzar.setEnabled(true);
		});


		//RETIRARSE
		botonRetirar.addClickListener(e -> {

			botonDameCarta.setEnabled(false);
			botonApostar.setEnabled(false);
			botonMePlanto.setEnabled(false);
			botonSeparar.setEnabled(false);
			botonDameCartaSegundaMano.setEnabled(false);
			botonMePlantoSegundaMano.setEnabled(false);
			botonNuevoJuego.setEnabled(true);
			botonRetirar.setEnabled(false);
			botonComenzar.setEnabled(false);
			
			
		});

		
		//NUEVO JUEGO
		botonNuevoJuego.addClickListener(e -> {
				Page.getCurrent().reload();
		});
		
		//BOTONES DE INICIO
		botonComenzar.setEnabled(false);
		horizontalLayout.addComponents(botonComenzar, botonApostar, apuesta, dinero);



		// BOTON DAME CARTA
		botonDameCarta.addClickListener(e -> {
			tapeteFormNuevaCarta = new TapeteForm(myUI);
			tapeteFormNuevaCarta.setNuevaCarta(baraja.getNuevaCarta());
			tapeteFormJugador.addComponent(tapeteFormNuevaCarta);
			manoJugador.anhadirCarta(tapeteFormNuevaCarta.cartaNueva);
			puntuacion.setValue("Puntuación Jugador: " + manoJugador.getPuntuacion());
			
			if(manoJugador.getPuntuacion() > PUNTUACION_21) {
				pierde();
				botonNuevoJuego.setEnabled(true);
				botonDameCarta.setEnabled(false);
				botonApostar.setEnabled(false);
				botonMePlanto.setEnabled(false);
				botonSeparar.setEnabled(false);
			}
			
		});

		// ME PLANTO
		botonMePlanto.addClickListener(e -> {
			
			
			tapeteFormNuevaCartaDealer = new TapeteForm(myUI);
			tapeteFormNuevaCartaDealer.setNuevaCarta(baraja.getNuevaCarta());
			tapeteFormDealer.addComponent(tapeteFormNuevaCartaDealer);
			
			manoDealer.anhadirCartaDealer(tapeteFormNuevaCartaDealer.cartaNueva);
			puntuacionDealerLabel.setValue("Puntuación Dealer: " + manoDealer.getPuntuacionDealer());
			
			removeComponent(0, 0);
			addComponent(puntuacionDealerLabel, 0, 0);
			
			
			if(manoJugador.getPuntuacion() < PUNTUACION_21 && manoDealer.getPuntuacionDealer() < manoJugador.getPuntuacion()) {
				gana();
			}else {
				
				botonDameCarta.setEnabled(false);
				botonApostar.setEnabled(false);
				botonMePlanto.setEnabled(false);
				botonSeparar.setEnabled(false);
				botonNuevoJuego.setEnabled(true);
				pierde();
			}
			
			
			mePlanto(myUI, baraja, manoDealer, manoJugador, botonDameCarta, botonDameCartaSegundaMano, botonMePlanto,
					botonMePlantoSegundaMano);
			
		});

		
		
		// Funcionalidad para separar mano cuando son las cartas iguales
		// SEPARAR
		if (manoJugador.getCarta(1).getNumero() == manoJugador.getCarta(2).getNumero()) {
			botonSeparar.setEnabled(true);
			botonSeparar.addClickListener(e -> {

				horizontalLayoutSeparar = new HorizontalLayout();
				
				botonSeparar.setEnabled(false);
				addComponent(horizontalLayoutSeparar, 1, 2);

				tapeteFormJugador.setMano(baraja.getManoSeparada1(manoJugador));
				tapeteFormJugadorNuevo = new TapeteForm(myUI);
				tapeteFormJugadorNuevo.setMano(baraja.getManoSeparada2(manoJugador));
				horizontalLayoutSeparar.addComponent(tapeteFormJugadorNuevo);
			});
		}
		
		
		// COMENZAR
		puntuacion.setValue("Puntuación Jugador: " + manoJugador.getPuntuacion());
		puntuacionDealerLabel.setValue("Puntuación Dealer: " + manoDealer.getPuntuacionDealer());
		botonComenzar.addClickListener(e -> {

			removeComponent(0, 0);
			removeComponent(0, 1);
			addComponent(tapeteFormDealer, 0, 1);
			removeComponent(0, 2);
			addComponent(tapeteFormJugador, 0, 2);
			removeComponent(0, 5);
			addComponent(puntuacion, 0, 5);
			addComponent(puntuacionDealerLabel, 0, 0);
			removeComponent(1, 6);
			addComponent(dinero, 1, 6);
			int apostado = dineroInicial - Integer.parseInt(apuesta.getValue());
			dinero.setValue("Dinero restante: " + apostado);
			
			//LAYOUT BOTONES
			horizontalLayout.addComponents(botonComenzar, botonApostar, botonRetirar, botonDameCarta, botonDameCartaSegundaMano, botonMePlanto, 
					botonMePlantoSegundaMano, botonSeparar, botonNuevoJuego, apuesta, dinero);
			


		});
		
		setRows(8);
		setColumns(2);
		
		addComponent(imagen, 0, 0);
		addComponent(horizontalLayout, 0, 3);
	}
	
	
	
	private void gana() {
		Label gana = new Label("HAS GANADO");
		gana.addStyleName("h1");
		removeComponent(0,  7);
		addComponent(gana, 0, 7);
	}
	
	
	private void pierde() {
		Label pierde = new Label("HAS PERDIDO");
		pierde.addStyleName("h1");
		removeComponent(0,  7);
		addComponent(pierde, 0, 7);
	
		
	}
	
	
	

	private void mePlanto(MyUI myUI, Baraja baraja, Mano manoDealer, Mano manoJugador, Button botonDameCarta,
			Button botonDameCartaSegundaMano, Button botonMePlanto, Button botonMePlantoSegundaMano) {

		if (horizontalLayoutSeparar == null) {

			botonDameCarta.setVisible(!botonDameCarta.isVisible());
			jugadoresYaPlantados.add(jugador1);

			if (jugadoresYaPlantados.size() == jugadores.size()) {

				int puntuacionDealer = manoDealer.getPuntuacionDealer();
				int puntuacionJugador = manoJugador.getPuntuacion();

				if (puntuacionDealer < PUNTUACION_17) {

					tapeteFormNuevaCartaDealer = new TapeteForm(myUI);
					tapeteFormNuevaCartaDealer.setNuevaCarta(baraja.getNuevaCarta());
					tapeteFormDealer.addComponent(tapeteFormNuevaCartaDealer);
					
				} else {
					for (int i = 0; i < jugadores.size(); i++) {
						if (puntuacionDealer < PUNTUACION_21) {
							if (puntuacionDealer > puntuacionJugador) {
								jugadores.get(i)
										.setDinero(jugadores.get(i).getDinero() - jugadores.get(i).getApuesta());
								jugadores.get(i).apostar(0);
							} else if (puntuacionDealer == puntuacionJugador) {
								jugadores.get(i).apostar(0);
							} else if (puntuacionDealer < puntuacionJugador) {
								jugadores.get(i)
										.setDinero(jugadores.get(i).getDinero() + jugadores.get(i).getApuesta());
								jugadores.get(i).apostar(0);
							}
						} else if (puntuacionDealer == PUNTUACION_21) {
							if (puntuacionJugador == PUNTUACION_21) {
								jugadores.get(i).apostar(0);
							} else {
								jugadores.get(i)
										.setDinero(jugadores.get(i).getDinero() - jugadores.get(i).getApuesta());
								jugadores.get(i).apostar(0);
							}
						} else {
							if (puntuacionJugador < PUNTUACION_21) {
								jugadores.get(i)
										.setDinero(jugadores.get(i).getDinero() + jugadores.get(i).getApuesta());
								jugadores.get(i).apostar(0);
							} else if (puntuacionJugador == PUNTUACION_21) {
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

					int puntuacionDealer = manoDealer.getPuntuacionDealer();
					int puntuacionJugador = manoJugador.getPuntuacion();

					if (puntuacionDealer < PUNTUACION_17) {
						tapeteFormNuevaCartaDealer = new TapeteForm(myUI);
						tapeteFormNuevaCartaDealer.setNuevaCarta(baraja.getNuevaCarta());
						tapeteFormDealer.addComponent(tapeteFormNuevaCartaDealer);
					} else {
						for (int i = 0; i < jugadores.size(); i++) {
							if (puntuacionDealer < PUNTUACION_21) {
								if (puntuacionDealer > puntuacionJugador) {
									jugadores.get(i)
											.setDinero(jugadores.get(i).getDinero() - jugadores.get(i).getApuesta());
									jugadores.get(i).apostar(0);
									str = "Ganador: Banca";
								} else if (puntuacionDealer == puntuacionJugador) {
									jugadores.get(i).apostar(0);
									str = "Empate";
								} else if (puntuacionDealer < puntuacionJugador) {
									jugadores.get(i)
											.setDinero(jugadores.get(i).getDinero() + jugadores.get(i).getApuesta());
									jugadores.get(i).apostar(0);
									str = "Ganador: " + jugadores.get(i).getNombre();
								}
							} else if (puntuacionDealer == PUNTUACION_21) {
								if (puntuacionJugador == PUNTUACION_21) {
									jugadores.get(i).apostar(0);
									str = "Empate";
								} else {
									jugadores.get(i)
											.setDinero(jugadores.get(i).getDinero() - jugadores.get(i).getApuesta());
									jugadores.get(i).apostar(0);
									str = "Ganador: Banca";
								}
							} else {
								if (puntuacionJugador < PUNTUACION_21) {
									jugadores.get(i)
											.setDinero(jugadores.get(i).getDinero() + jugadores.get(i).getApuesta());
									jugadores.get(i).apostar(0);
									str = "Ganador: " + jugadores.get(i).getNombre();
								} else if (puntuacionJugador == PUNTUACION_21) {
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
				// addComponent(ganador, 0, 5);
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