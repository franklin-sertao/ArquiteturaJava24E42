package br.edu.infnet.franklin.model.domain;

public class IngredienteUnitario extends Ingrediente {
	private int quantidadePorLote;
	private String tipo;

	public int getQuantidadePorLote() {
		return quantidadePorLote;
	}

	public void setQuantidadePorLote(int quantidadePorLote) {
		this.quantidadePorLote = quantidadePorLote;
	}

	public String getTipo(){
		return tipo;
	}

	public void setTipo(String tipo){
		this.tipo = tipo;
	}



}
