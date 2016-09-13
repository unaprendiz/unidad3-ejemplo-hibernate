package ar.edu.unq.unidad3.wop.modelo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ar.edu.unq.unidad3.wop.modelo.exception.MuchoPesoException;

@Entity
@Table(name="PERSONAJES")
public class Personaje  {

	@Id
	private String nombre;
	
	@Column(name="hist", length=100000, nullable=false)
	private String historia;
	
	private int pesoMaximo;
	private int xp;
	private int vida;
	
	@OneToMany(mappedBy="owner", cascade=CascadeType.ALL)
	private Set<Item> inventario = new HashSet<>();
	
	protected Personaje() {
	}
	
	public Personaje(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return this.nombre;
	}

	public void setHistoria(String historia) {
		this.historia = historia;
	}
	
	public int getPesoMaximo() {
		return this.pesoMaximo;
	}
	public void setPesoMaximo(int pesoMaximo) {
		this.pesoMaximo = pesoMaximo;
	}
	
	public int getXp() {
		return this.xp;
	}
	public void setXp(int xp) {
		this.xp = xp;
	}
	
	public int getVida() {
		return this.vida;
	}
	public void setVida(int vida) {
		this.vida = vida;
	}
	
	public Set<Item> getInventario() {
		return this.inventario;
	}
	public void setInventario(Set<Item> inventario) {
		this.inventario = inventario;
	}

	public void recoger(Item item) {
		int pesoActual = this.getPesoActual();
		if (pesoActual + item.getPeso() > this.pesoMaximo) {
			throw new MuchoPesoException(this, item);
		}
		
		this.inventario.add(item);
		item.setOwner(this);
	}

	private int getPesoActual() {
		int pesoActual = 0;
		for (Item item : this.inventario) {
			pesoActual += item.getPeso();
		}
		return pesoActual;
	}
	
	@Override
	public String toString() {
		return this.nombre;
	}

}
