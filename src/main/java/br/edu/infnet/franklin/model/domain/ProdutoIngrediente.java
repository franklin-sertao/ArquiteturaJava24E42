package br.edu.infnet.franklin.model.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ProdutoIngrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double quantidade;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "idProduto")
	@JsonBackReference
    private Produto produto;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "idIngrediente")
	@JsonManagedReference
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
