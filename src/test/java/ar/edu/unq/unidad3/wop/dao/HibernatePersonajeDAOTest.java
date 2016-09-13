package ar.edu.unq.unidad3.wop.dao;

import org.junit.Assert;
import org.junit.Test;

import ar.edu.unq.unidad3.wop.dao.PersonajeDAO;
import ar.edu.unq.unidad3.wop.dao.impl.HibernatePersonajeDAO;
import ar.edu.unq.unidad3.wop.modelo.Item;
import ar.edu.unq.unidad3.wop.modelo.Personaje;

/**
 * TODO: Esta implementcion depende de que exista una
 * base de datos corriendo.
 * 
 * @author Claudio Fernandez
 */
public class HibernatePersonajeDAOTest {
	
	private PersonajeDAO dao = new HibernatePersonajeDAO();
	
	@Test
	public void al_guardar_y_luego_recuperar_se_obtiene_objetos_similares() {
		Personaje maguito = new Personaje("Maguito");
		maguito.setPesoMaximo(15);
		maguito.setVida(198);
		maguito.setXp(2500);
		maguito.setHistoria("Este es un maguito");
		maguito.recoger(new Item("Tunica gris", 1));
		maguito.recoger(new Item("Baculo gris", 5));
		
		this.dao.guardar(maguito);
		
		Personaje maguitoRecuperado = this.dao.recuperar("Maguito");
		Assert.assertEquals("Maguito", maguitoRecuperado.getNombre());
		Assert.assertEquals(15, maguitoRecuperado.getPesoMaximo());
		Assert.assertEquals(2500, maguitoRecuperado.getXp());
		Assert.assertEquals(2, maguitoRecuperado.getInventario().size());
		
	}

}
