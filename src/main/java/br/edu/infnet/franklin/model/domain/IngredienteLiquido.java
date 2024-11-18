package br.edu.infnet.franklin.model.domain;


public class IngredienteLiquido extends Ingrediente {
	private float volume;
	private String tipo; // líquido, pasta
	

	// ----------------- Métodos -----------------


	
	// ------------ Getters e Setters ------------


	public float getVolume() {
		return volume;
	}

	public void setVolume(float volume) {
		this.volume = volume;
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
				this.getVolume(),
				this.getPreco(),
				this.getTipo()
		);
	}
	
}
