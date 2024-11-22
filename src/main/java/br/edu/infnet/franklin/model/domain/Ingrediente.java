package br.edu.infnet.franklin.model.domain;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
public abstract class Ingrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@NotNull (message = "O campo nome é obrigatório")
    private String nome;
	
	@PositiveOrZero (message = "O campo preço total deve ser maior que zero")
    private Double precoTotal;

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

    public void setOrganico(boolean organico) {
        this.organico = organico;
    }

	public abstract Double getPrecoUnitario();
    public abstract String getTipo();
}
