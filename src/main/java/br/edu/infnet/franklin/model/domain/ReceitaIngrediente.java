package br.edu.infnet.franklin.model.domain;

import jakarta.persistence.*;

@Entity
public class ReceitaIngrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double quantidade;

    @ManyToOne(optional = false)
    @JoinColumn(name = "receita_id")
    private Receita receita;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ingrediente_id")
    private Ingrediente ingrediente;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public Receita getReceita() {
        return receita;
    }

    public Ingrediente getIngrediente() {
        return ingrediente;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public void setReceita(Receita receita) {
        this.receita = receita;
    }

    public void setIngrediente(Ingrediente ingrediente) {
        this.ingrediente = ingrediente;
    }
}
