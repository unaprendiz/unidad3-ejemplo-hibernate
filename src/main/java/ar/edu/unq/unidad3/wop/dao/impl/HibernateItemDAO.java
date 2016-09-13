package ar.edu.unq.unidad3.wop.dao.impl;

import org.hibernate.Session;

import ar.edu.unq.unidad3.wop.dao.ItemDAO;
import ar.edu.unq.unidad3.wop.modelo.Item;
import ar.edu.unq.unidad3.wop.service.runner.Runner;

/**
 * Una implementacion de {@link ItemDAO} que persiste
 * en una base de datos relacional utilizando JDBC
 * 
 * @author Claudio Fernandez
 */
public class HibernateItemDAO implements ItemDAO {

	@Override
	public void guardar(Item item) {
		Session session = Runner.getCurrentSession();
		session.save(item);
	}

	@Override
	public Item recuperar(String nombre) {
		Session session = Runner.getCurrentSession();
		return session.get(Item.class, nombre);
	}

}
