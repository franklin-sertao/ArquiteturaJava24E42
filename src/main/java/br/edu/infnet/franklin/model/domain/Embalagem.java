package br.edu.infnet.franklin.model.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Embalagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;
    private Integer quantidadePorPacote;
    private BigDecimal precoPacote;

    @ManyToMany(mappedBy = "embalagens")
    private List<Produto> produtos = new ArrayList<>();

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getQuantidadePorPacote() {
        return quantidadePorPacote;
    }

    public BigDecimal getPrecoPacote() {
        return precoPacote;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setQuantidadePorPacote(Integer quantidadePorPacote) {
        this.quantidadePorPacote = quantidadePorPacote;
    }

    public void setPrecoPacote(BigDecimal precoPacote) {
        this.precoPacote = precoPacote;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}
