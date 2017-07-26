package es.cic.taller.blackjack;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.vaadin.server.FileResource;
import com.vaadin.server.Resource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.VerticalLayout;



public class TapeteForm extends HorizontalLayout {

	private HorizontalLayout horizontalLayout = new HorizontalLayout();
	private VerticalLayout verticalLayout = new VerticalLayout();
	
	private Image imagen1 = new Image();
	private Image imagen2 = new Image();
	private Image imagen3 = new Image();
	Carta cartaNueva;

	
	
	private MyUI myUI;

	
	public TapeteForm(MyUI myUI) {
		this.myUI = myUI;


		horizontalLayout.addComponents(imagen1, imagen2);
		
		
		addComponents(horizontalLayout);
}

	public void setMano(Mano mano) {
		cargaCarta(mano.getCarta(1), imagen1);
		cargaCarta(mano.getCarta(2), imagen2);
	}
	
	public Carta setNuevaCarta(Mano mano) {
		cartaNueva = mano.getCarta(1);
		cargaCarta(cartaNueva, imagen3);
		horizontalLayout.addComponent(imagen3);
		return cartaNueva;
	}
	
	private Resource getImageResource(String recurso) {
		String basepath = VaadinService.getCurrent()
                .getBaseDirectory().getAbsolutePath();
		FileResource resource = new FileResource(new File(basepath +
                "/images/" + recurso));
        return resource;
	}
	
	private void cargaCarta(Carta carta, Image imagen) {
		imagen.setSource(getImageResource(carta.getNombreFichero()));
		imagen.setWidth("100px");
		imagen.setHeight("200px");
	}
	
	
}
