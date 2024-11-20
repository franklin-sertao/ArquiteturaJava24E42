package br.edu.infnet.franklin.model.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("seco")
public class IngredienteSeco extends Ingrediente {

    private Integer pesoLiquidoEmGramas;

    // Getters e Setters
    public Integer getPesoLiquidoEmGramas() {
        return pesoLiquidoEmGramas;
    }

    public void setPesoLiquidoEmGramas(Integer pesoLiquidoEmGramas) {
        this.pesoLiquidoEmGramas = pesoLiquidoEmGramas;
    }

    @Override
    public String getTipo() {
        return "Seco";
    }
}
