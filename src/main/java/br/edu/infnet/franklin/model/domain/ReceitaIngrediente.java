package br.edu.infnet.franklin.model.domain;

import jakarta.persistence.*;

@Entity
public class ReceitaIngrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "receita_id")
    private Receita receita;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ingrediente_id")
    private Ingrediente ingrediente;

    private Double quantidade;

    public Long getId() {
        return id;
    }

    public Receita getReceita() {
        return receita;
    }

    public Ingrediente getIngrediente() {
        return ingrediente;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setReceita(Receita receita) {
        this.receita = receita;
    }

    public void setIngrediente(Ingrediente ingrediente) {
        this.ingrediente = ingrediente;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }
}
