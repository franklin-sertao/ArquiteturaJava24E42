package br.edu.infnet.franklin.model.domain;

import jakarta.persistence.*;

@Entity
public class ProdutoIngrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double quantidade;

    @ManyToOne(optional = false)
    @JoinColumn(name = "produto_id")
    private Produto produto;

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

    public Produto getProduto() {
        return produto;
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

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public void setIngrediente(Ingrediente ingrediente) {
        this.ingrediente = ingrediente;
    }
}
