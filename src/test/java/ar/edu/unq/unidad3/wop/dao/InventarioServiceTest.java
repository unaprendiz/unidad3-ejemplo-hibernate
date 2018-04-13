package ar.edu.unq.unidad3.wop.dao;

import java.util.Collection;

import org.junit.After;
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
import ar.edu.unq.unidad3.wop.service.runner.SessionFactoryProvider;

public class InventarioServiceTest {
	
	private InventarioService service;
	private TestService testService;
	
	@Before
	public void prepare() {
		PersonajeDAO personajeDAO = new HibernatePersonajeDAO();
		ItemDAO itemDAO = new HibernateItemDAO();

		this.service = new InventarioService(personajeDAO, itemDAO);
		this.testService = new TestService();
		
		this.testService.crearEntidad(new Item("Tunica", 100));
		this.testService.crearEntidad(new Item("Baculo", 50));

		Personaje maguin = new Personaje("Maguin");
		maguin.setPesoMaximo(70);
		maguin.setVida(10);
		this.testService.crearEntidad(maguin);
		
		Personaje debilucho = new Personaje("Debilucho");
		debilucho.setPesoMaximo(1000);
		debilucho.setVida(1);
		this.testService.crearEntidad(debilucho);
	}
	
	@After
	public void cleanup() {
		//Destroy cierra la session factory y fuerza a que, la proxima vez, una nueva tenga
		//que ser creada.
		//
		//Al tener hibernate configurado con esto <property name="hibernate.hbm2ddl.auto">create-drop</property>
		//al crearse una nueva session factory todo el schema será destruido y creado desde cero.
		SessionFactoryProvider.destroy();
	}
	
	
	@Test
	public void test_recoger() {
		this.service.recoger("Maguin", "Baculo");
		
		Personaje maguin = this.testService.recuperarEntidad(Personaje.class, "Maguin");
		Assert.assertEquals("Maguin", maguin.getNombre());

		Assert.assertEquals(1, maguin.getInventario().size());

		Item baculo = maguin.getInventario().iterator().next();
		Assert.assertEquals("Baculo", baculo.getNombre());

		Assert.assertSame(baculo.getOwner(), maguin);
	}
	
	@Test
	public void test_get_all() {
		Collection<Item> items = this.service.getAllItems();

		Assert.assertEquals(2, items.size());

		Item baculo = new Item("Baculo", 100);
		Assert.assertTrue(items.contains(baculo));
	}
	
	@Test
	public void test_get_mas_pesados() {
		Collection<Item> items = this.service.getMasPesdos(10);
		Assert.assertEquals(2, items.size());

		Collection<Item> items2 = this.service.getMasPesdos(80);
		Assert.assertEquals(1, items2.size());
	}
	
	@Test
	public void test_get_items_debiles() {
		Collection<Item> items = this.service.getItemsPersonajesDebiles(5);
		Assert.assertEquals(0, items.size());

		this.service.recoger("Maguin", "Baculo");
		this.service.recoger("Debilucho", "Tunica");
		
		items = this.service.getItemsPersonajesDebiles(5);
		Assert.assertEquals(1, items.size());
		Assert.assertEquals("Tunica", items.iterator().next().getNombre());

	}

	@Test
	public void test_get_mas_pesado() {
		Item item = this.service.getHeaviestItem();
		Assert.assertEquals("Tunica", item.getNombre());
	}
	
}
