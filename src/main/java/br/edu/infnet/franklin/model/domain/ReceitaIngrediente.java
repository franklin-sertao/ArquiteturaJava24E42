package br.edu.infnet.franklin.model.domain;

public class ReceitaIngrediente {
	
	// ---------------- Atributos ----------------
	private int id;
	private Receita receita;
	private Ingrediente ingrediente;
	private float quantidade;

	// --------------- Construtor ----------------
	public ReceitaIngrediente(Receita receita, Ingrediente ingrediente, float quantidade) {
		this.receita = receita;
		this.ingrediente = ingrediente;
		this.quantidade = quantidade;
	}
	
	// ----------------- Métodos -----------------

	

	// ------------ Getters e Setters ------------

	public int getId() {
		return id;
	}

	public Receita getReceita() {
		return receita;
	}

	public Ingrediente getIngrediente() {
		return ingrediente;
	}

	public float getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(float quantidade){
		this.quantidade = quantidade;
	}

	public float getPreco() {
		return this.ingrediente.getPreco() * this.quantidade;
	}

	
}
