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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.nombre == null) ? 0 : this.nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (this.nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!this.nombre.equals(other.nombre))
			return false;
		return true;
	}
	
	
}
