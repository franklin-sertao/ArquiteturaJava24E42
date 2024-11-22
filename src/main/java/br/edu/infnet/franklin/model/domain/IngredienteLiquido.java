package br.edu.infnet.franklin.model.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@DiscriminatorValue("liquido")
public class IngredienteLiquido extends Ingrediente {

	@PositiveOrZero (message = "O campo Volume(ml) deve ser maior ou igual a zero")
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
