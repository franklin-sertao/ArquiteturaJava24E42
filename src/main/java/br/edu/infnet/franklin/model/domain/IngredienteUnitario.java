package br.edu.infnet.franklin.model.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@DiscriminatorValue("unitario")
public class IngredienteUnitario extends Ingrediente {

	@PositiveOrZero (message = "O campo Quantidade deve ser maior ou igual a zero")
    private Integer quantidadeUnidades;

    // Getters e Setters
    public Integer getQuantidadeUnidades() {
        return quantidadeUnidades;
    }

    public void setQuantidadeUnidades(Integer quantidadeUnidades) {
        this.quantidadeUnidades = quantidadeUnidades;
    }

    @Override
    public String getTipo() {
        return "unitario";
    }

	@Override
	public Double getPrecoUnitario() {
		return getQuantidadeUnidades() != null ? getPrecoTotal() / getQuantidadeUnidades() : null;
	}
}
