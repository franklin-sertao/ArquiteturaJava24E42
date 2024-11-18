package br.edu.infnet.franklin.model.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Receita {

	// ---------------- Atributos ----------------

	private String nome;
	private String descricao;
	private String modoPreparo;
	private int tempoPreparo;
	private int rendimento;
	private String unidadeRendimento; // porção, fatia, unidade, etc.

	// ----------------- Métodos -----------------

	public float getPreco() {
		float preco = 0;
		
		for (ReceitaIngrediente receitaIngrediente : ingredientes) {
			preco += receitaIngrediente.getPreco();
		}

		return preco;
	}

	@Override
	public String toString() {
		String out = "";
		
		out += String.format("Receita: %s%n", nome);
		out += String.format("Descrição: %s%n", descricao);
		out += String.format("Tempo de preparo: %d minutos%n", tempoPreparo);
		out += String.format("Rendimento: %d %s%n", rendimento, unidadeRendimento);
		out += String.format("Preço: R$ %.2f%n", getPreco());
		out += String.format("Ingredientes:%n");

		for (ReceitaIngrediente receitaIngrediente : ingredientes) {
			Ingrediente ingrediente = receitaIngrediente.getIngrediente();
			out += String.format("%s%s de %s$n",
					receitaIngrediente.getQuantidade(),
					ingrediente.getUnidadeMedida(),
					ingrediente.getNome()
			);
		}

		out += String.format("Modo de preparo: %s%n", modoPreparo);

		return out;
	}
	
	public void addIngrediente(Ingrediente ingrediente, float quantidade) {
		ReceitaIngrediente receitaIngrediente = new ReceitaIngrediente(this, ingrediente, quantidade);
		ingredientes.add(receitaIngrediente);
	}

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

	public List<ReceitaIngrediente> getIngredientes() {
		return ingredientes;
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
