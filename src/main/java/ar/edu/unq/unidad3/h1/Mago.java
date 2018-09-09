package ar.edu.unq.unidad3.h1;

import javax.persistence.Entity;

@Entity
public class Mago extends Persona {

	private int poder;

	public int getPoder() {
		return poder;
	}

	public void setPoder(int poder) {
		this.poder = poder;
	}
}
