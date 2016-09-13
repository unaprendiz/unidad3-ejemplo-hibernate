package ar.edu.unq.unidad3.wop.service;

import ar.edu.unq.unidad3.wop.dao.ItemDAO;
import ar.edu.unq.unidad3.wop.dao.PersonajeDAO;
import ar.edu.unq.unidad3.wop.modelo.Item;
import ar.edu.unq.unidad3.wop.modelo.Personaje;
import ar.edu.unq.unidad3.wop.service.runner.Runner;

public class InventarioService {
	
	private PersonajeDAO personajeDAO;
	private ItemDAO itemDAO;
	
	public InventarioService(PersonajeDAO personajeDAO, ItemDAO itemDAO) {
		this.personajeDAO = personajeDAO;
		this.itemDAO = itemDAO;
	}
	
	public void recoger(String nombrePersonaje, String nombreItem) {
		Runner.runInSession(() -> {
			Personaje personaje = personajeDAO.recuperar(nombrePersonaje);
			Item item = itemDAO.recuperar(nombreItem);
			personaje.recoger(item);
			
			return null;
		});
	}

}
