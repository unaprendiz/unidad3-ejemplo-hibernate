package ar.edu.unq.unidad3.h1;

import javax.persistence.Entity;

@Entity
public class Caballero extends Persona {

	private int embestida;

	public int getEmbestida() {
		return embestida;
	}

	public void setEmbestida(int embestida) {
		this.embestida = embestida;
	}
}
