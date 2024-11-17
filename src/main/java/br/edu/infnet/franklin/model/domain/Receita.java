package br.edu.infnet.franklin.model.domain;

import java.util.List;

public class Receita {

	private String nome;
	private String descricao;
	private List <Ingrediente> ingredientes;
	private String modoPreparo;
	private int tempoPreparo;
	private int rendimento;
	private String unidadeRendimento;


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

	public List<Ingrediente> getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(List<Ingrediente> ingredientes) {
		this.ingredientes = ingredientes;
	}

	public String getModoPreparo() {
		return modoPreparo;
	}

	public void setModoPreparo(String modoPreparo) {
		this.modoPreparo = modoPreparo;
	}

	public int getTempoPreparo() {
		return tempoPreparo;
	}

	public void setTempoPreparo(int tempoPreparo) {
		this.tempoPreparo = tempoPreparo;
	}

	public int getRendimento() {
		return rendimento;
	}

	public void setRendimento(int rendimento) {
		this.rendimento = rendimento;
	}

	public String getUnidadeRendimento() {
		return unidadeRendimento;
	}

	public void setUnidadeRendimento(String unidadeRendimento) {
		this.unidadeRendimento = unidadeRendimento;
	}

}
