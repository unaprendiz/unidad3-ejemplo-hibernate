package ar.edu.unq.unidad3.wop.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.edu.unq.unidad3.wop.dao.impl.HibernateItemDAO;
import ar.edu.unq.unidad3.wop.dao.impl.HibernatePersonajeDAO;
import ar.edu.unq.unidad3.wop.modelo.Item;
import ar.edu.unq.unidad3.wop.modelo.Personaje;
import ar.edu.unq.unidad3.wop.service.InventarioService;
import ar.edu.unq.unidad3.wop.service.TestService;
import ar.edu.unq.unidad3.wop.service.runner.Runner;

public class InventarioServiceTest {
	
	private InventarioService service;
	private TestService testService;
	
	@Before
	public void prepare() {
		PersonajeDAO personajeDAO = new HibernatePersonajeDAO();
		ItemDAO itemDAO = new HibernateItemDAO();

		this.service = new InventarioService(personajeDAO, itemDAO);
		this.testService = new TestService();
		
		this.testService.crearEntidad(new Item("Baculo", 50));

		Personaje maguin = new Personaje("Maguin");
		maguin.setPesoMaximo(70);
		this.testService.crearEntidad(maguin);
	}
	
	@Test
	public void test_recoger() {
		this.service.recoger("Maguin", "Baculo");
		
		Runner.runInSession(() -> {
			Personaje maguin = testService.recuperarEntidad(Personaje.class, "Maguin");
			Assert.assertEquals("Maguin", maguin.getNombre());
			
			Assert.assertEquals(1, maguin.getInventario().size());
			
			Item baculo = maguin.getInventario().iterator().next();
			Assert.assertEquals("Baculo", baculo.getNombre());
			
			Assert.assertSame(baculo.getOwner(), maguin);

			return null;
		});
	}

}
