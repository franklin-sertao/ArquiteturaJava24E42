package br.edu.infnet.franklin.model.domain;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
public abstract class Ingrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Double precoTotal;
	private Double quantidade;
    private boolean organico;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Double getPrecoTotal() {
        return precoTotal;
    }

	public Double getQuantidade() {
		return quantidade;
	}

    public boolean isOrganico() {
        return organico;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPrecoTotal(Double precoTotal) {
        this.precoTotal = precoTotal;
    }

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}

    public void setOrganico(boolean organico) {
        this.organico = organico;
    }

	public abstract Double getPrecoUnitario();
    public abstract String getTipo();
}
