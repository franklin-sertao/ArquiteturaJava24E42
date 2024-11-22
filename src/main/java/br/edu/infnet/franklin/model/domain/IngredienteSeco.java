package br.edu.infnet.franklin.model.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("seco")
public class IngredienteSeco extends Ingrediente {

    private Double pesoLiquidoEmGramas;

    // Getters e Setters
    public Double getPesoLiquidoEmGramas() {
        return pesoLiquidoEmGramas;
    }

    public void setPesoLiquidoEmGramas(Double pesoLiquidoEmGramas) {
        this.pesoLiquidoEmGramas = pesoLiquidoEmGramas;
    }

    @Override
    public String getTipo() {
        return "seco";
    }

	@Override
	public Double getPrecoUnitario() {
		return pesoLiquidoEmGramas != null ? getPrecoTotal() / getPesoLiquidoEmGramas() : null;
	}
}
