package ar.edu.unq.unidad3.wop.modelo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Item {

	@Id
	private String nombre;
	
	private int peso;

	@ManyToOne
	private Personaje owner;
	
	protected Item() {
	}
	
	public Item(String nombre, int peso) {
		this.nombre = nombre;
		this.peso = peso;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	public int getPeso() {
		return this.peso;
	}
	
	public Personaje getOwner() {
		return this.owner;
	}
	
	public void setOwner(Personaje owner) {
		this.owner = owner;
	}
	
	@Override
	public String toString() {
		return this.getNombre();
	}
	
}
