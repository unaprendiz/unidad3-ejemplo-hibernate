package ar.edu.unq.unidad3.wop.dao.impl;

import org.hibernate.Session;

import ar.edu.unq.unidad3.wop.dao.PersonajeDAO;
import ar.edu.unq.unidad3.wop.modelo.Personaje;
import ar.edu.unq.unidad3.wop.service.runner.Runner;

/**
 * Una implementacion de {@link PersonajeDAO} que persiste
 * en una base de datos relacional utilizando JDBC
 * 
 * @author Claudio Fernandez
 */
public class HibernatePersonajeDAO implements PersonajeDAO {

	@Override
	public void guardar(Personaje personaje) {
		Session session = Runner.getCurrentSession();
		session.save(personaje);
	}

	@Override
	public Personaje recuperar(String nombre) {
		Session session = Runner.getCurrentSession();
		return session.get(Personaje.class, nombre);
	}

}
