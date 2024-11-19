package br.edu.infnet.franklin.model.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("liquido")
public class IngredienteLiquido extends Ingrediente {

    private Integer volumeLiquidoEmML;

    // Getters e Setters
    public Integer getVolumeLiquidoEmML() {
        return volumeLiquidoEmML;
    }

    public void setVolumeLiquidoEmML(Integer volumeLiquidoEmML) {
        this.volumeLiquidoEmML = volumeLiquidoEmML;
    }

    @Override
    public String getTipo() {
        return "Líquido";
    }
}
