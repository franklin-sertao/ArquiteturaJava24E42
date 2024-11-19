package br.edu.infnet.franklin.model.domain;

import jakarta.persistence.*;

@Entity
public class ProdutoIngrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double quantidade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingrediente_id")
    private Ingrediente ingrediente;

    // Construtores
    public ProdutoIngrediente() {
    }

    // Getters e Setters
    public Long getId() { return id; }

    public Double getQuantidade() { return quantidade; }

    public void setQuantidade(Double quantidade) { this.quantidade = quantidade; }

    public Produto getProduto() { return produto; }

    public void setProduto(Produto produto) { this.produto = produto; }

    public Ingrediente getIngrediente() { return ingrediente; }

    public void setIngrediente(Ingrediente ingrediente) { this.ingrediente = ingrediente; }
}
