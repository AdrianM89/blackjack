package es.cic.taller.blackjack;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("mytheme")
public class MyUI extends UI {

	private VerticalLayout layout = new VerticalLayout();

	@Override
	protected void init(VaadinRequest vaadinRequest) {

		Baraja baraja = new Baraja();

		PantallaLayout pantallaLayout = new PantallaLayout(this, baraja);

		layout.addComponent(pantallaLayout);

		setContent(layout);

	}

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {
	}
}
