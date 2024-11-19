package br.edu.infnet.franklin.model.domain;

import jakarta.persistence.*;

@Entity
public class ProdutoReceita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @ManyToOne(optional = false)
    @JoinColumn(name = "receita_id")
    private Receita receita;

    private Double quantidade;

    public Long getId() {
        return id;
    }

    public Produto getProduto() {
        return produto;
    }

    public Receita getReceita() {
        return receita;
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

    public void setReceita(Receita receita) {
        this.receita = receita;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }
}
