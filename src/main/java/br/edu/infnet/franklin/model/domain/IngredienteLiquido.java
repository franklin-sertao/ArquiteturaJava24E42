package br.edu.infnet.franklin.model.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("liquido")
public class IngredienteLiquido extends Ingrediente {

    private Double volumeLiquidoEmML;

    // Getters e Setters
    public Double getVolumeLiquidoEmML() {
        return volumeLiquidoEmML;
    }

    public void setVolumeLiquidoEmML(Double volumeLiquidoEmML) {
        this.volumeLiquidoEmML = volumeLiquidoEmML;
    }

    @Override
    public String getTipo() {
        return "liquido";
    }

	@Override
	public Double getPrecoUnitario() {
		return getVolumeLiquidoEmML() != null ? getPrecoTotal() / getVolumeLiquidoEmML() : null;
	}
}
