package br.edu.infnet.franklin.model.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("unitario")
public class IngredienteUnitario extends Ingrediente {

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
