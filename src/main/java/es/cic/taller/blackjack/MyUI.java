package es.cic.taller.blackjack;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;


/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {
	
	private TapeteForm tapete1 = new TapeteForm(this);
	
	private VerticalLayout layout = new VerticalLayout();
	
    @Override
    protected void init(VaadinRequest vaadinRequest) {
    	Baraja baraja = new Baraja();
        
//        Mano mano1 = baraja.getManoJugador();
//        
//        tapete1.setMano(mano1);;

        
        PantallaLayout pantallaLayout = new PantallaLayout(this, baraja);
        
        
        
        layout.addComponent(pantallaLayout);
        
        setContent(layout);
        

        
}

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
