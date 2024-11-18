package br.edu.infnet.franklin.model.domain;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class IngredienteLiquido extends Ingrediente {

    private Integer volumeLiquidoEmML;

    // Construtores
    public IngredienteLiquido() {}

    // Getters e Setters

    public Integer getVolumeLiquidoEmML() {
        return volumeLiquidoEmML;
    }

    public void setVolumeLiquidoEmML(Integer volumeLiquidoEmML) {
        this.volumeLiquidoEmML = volumeLiquidoEmML;
    }

    @Override
    public BigDecimal getPrecoPorUnidade() {
        return getPrecoTotal().divide(new BigDecimal(getVolumeLiquidoEmML()));
    }
}
