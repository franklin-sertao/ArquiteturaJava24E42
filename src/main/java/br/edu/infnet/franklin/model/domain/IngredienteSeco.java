package br.edu.infnet.franklin.model.domain;

public class IngredienteSeco extends Ingrediente {

	// ---------------- Atributos ----------------
	private Float peso; // Float para poder ser nulo
	private String tipo; //inteiro, fracionado, em pó

	// ----------------- Métodos -----------------

	
	
	// ------------ Getters e Setters ------------ 

	public Float getPeso() {
		return peso;
	}

	public void setPeso(Float peso) {
		this.peso = peso;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	@Override
	public String toString() {
		return String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s",
				this.getUnidadeMedida(),
				this.getNome(),
				this.getFabricante(),
				this.getDescricao(),
				this.getImagem(),
				this.getCodigo(),
				this.getPeso(),
				this.getPreco(),
				this.getTipo()
		);
	}
	
}
