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

	
	
	private MyUI myUI;
	
	public TapeteForm(MyUI myUI) {
		this.myUI = myUI;


		horizontalLayout.addComponents(imagen1, imagen2, imagen3);
		
		
		addComponents(horizontalLayout);
}

	public void setMano(Mano mano) {
		
		
		cargaCarta(mano.getCarta1(), imagen1);
		cargaCarta(mano.getCarta2(), imagen2);
	}
	
	public void setNuevaCarta(Mano mano) {
		
		cargaCarta(mano.getCarta1(), imagen3);
		
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
