package br.edu.infnet.franklin.model.domain;


public abstract class Ingrediente {
	private String nome;
	private String descricao;
	private float  densidade;
	private String codigo;
	private String unidadeMedida;

	// ----------------- Métodos -----------------


	
	// ------------ Getters e Setters ------------
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public float getDensidade() {
		return densidade;
	}

	public void setDensidade(float densidade) {
		this.densidade = densidade;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidadeMedida(String unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}
}
