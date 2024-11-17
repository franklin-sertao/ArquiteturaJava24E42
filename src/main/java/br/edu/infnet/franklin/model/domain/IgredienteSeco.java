package br.edu.infnet.franklin.model.domain;

public class IgredienteSeco extends Ingrediente {
	private float peso;
	private String tipo; //inteiro, fracionado, em pó

	// ----------------- Métodos -----------------


	
	// ------------ Getters e Setters ------------ 

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
/* 	@Override
	public String toString() {
		return super.toString() + ";" + peso;
	} */
	
}
