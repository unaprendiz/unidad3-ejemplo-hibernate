package ar.edu.unq.unidad3.wop.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import ar.edu.unq.unidad3.wop.dao.PersonajeDAO;
import ar.edu.unq.unidad3.wop.modelo.Personaje;

/**
 * Una implementacion de {@link PersonajeDAO} que persiste
 * en una base de datos relacional utilizando JDBC
 * 
 * @author Claudio Fernandez
 */
public class HibernatePersonajeDAO implements PersonajeDAO {

	private final SessionFactory sessionFactory;

	public HibernatePersonajeDAO() {
		//TODO: por ahora lo hacemos así. Esté no es el lugar para inicializar toda la configuración
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		this.sessionFactory = configuration.buildSessionFactory();
	}

	//Por ahora tenemos bloques repetidos
	@Override
	public void guardar(Personaje personaje) {
		Session session = null;
		Transaction tx = null;
		try {
			session = this.sessionFactory.openSession();
			tx = session.beginTransaction();

			//hago lo que tengo que hacer
			session.save(personaje);
			
			tx.commit();

		} catch (RuntimeException e) {
			tx.rollback();
			throw e;
		} finally {
			session.close();
		}

	}

	//Por ahora tenemos bloques repetidos
	@Override
	public Personaje recuperar(String nombre) {
		Session session = null;
		Transaction tx = null;
		try {
			session = this.sessionFactory.openSession();
			tx = session.beginTransaction();

			//hago lo que tengo que hacer
			Personaje personaje = session.get(Personaje.class, nombre);
			
			//tomenlo con pinzas, esto no esta, no existe
			personaje.getInventario().size();
			
			tx.commit();
			
			return personaje;

		} catch (RuntimeException e) {
			tx.rollback();
			throw e;
		} finally {
			session.close();
		}
	}


}
