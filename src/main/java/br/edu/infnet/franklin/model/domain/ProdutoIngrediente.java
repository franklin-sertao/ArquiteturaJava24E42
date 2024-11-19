package br.edu.infnet.franklin.model.domain;

import jakarta.persistence.*;

@Entity
public class ProdutoIngrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ingrediente_id")
    private Ingrediente ingrediente;

    private Double quantidade;

    public Long getId() {
        return id;
    }

    public Produto getProduto() {
        return produto;
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

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public void setIngrediente(Ingrediente ingrediente) {
        this.ingrediente = ingrediente;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }
}
