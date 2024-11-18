package br.edu.infnet.franklin.model.domain;

import jakarta.persistence.Entity;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
public class IngredienteSeco extends Ingrediente {

    private Integer pesoLiquidoEmGramas;

    // Construtores
    public IngredienteSeco() {}

    // Getters e Setters

    public Integer getPesoLiquidoEmGramas() {
        return pesoLiquidoEmGramas;
    }

    public void setPesoLiquidoEmGramas(Integer pesoLiquidoEmGramas) {
        this.pesoLiquidoEmGramas = pesoLiquidoEmGramas;
    }

    @Override
    public BigDecimal getPrecoPorUnidade() {
        return getPrecoTotal().divide(new BigDecimal(getPesoLiquidoEmGramas()), RoundingMode.HALF_UP);
    }
}
