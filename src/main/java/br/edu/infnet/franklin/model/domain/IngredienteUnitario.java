package br.edu.infnet.franklin.model.domain;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class IngredienteUnitario extends Ingrediente {

    private Integer quantidadeUnidades;

    // Construtores
    public IngredienteUnitario() {}

    // Getters e Setters

    public Integer getQuantidadeUnidades() {
        return quantidadeUnidades;
    }

    public void setQuantidadeUnidades(Integer quantidadeUnidades) {
        this.quantidadeUnidades = quantidadeUnidades;
    }

    @Override
    public BigDecimal getPrecoPorUnidade() {
        return getPrecoTotal().divide(new BigDecimal(getQuantidadeUnidades()));
    }
}
