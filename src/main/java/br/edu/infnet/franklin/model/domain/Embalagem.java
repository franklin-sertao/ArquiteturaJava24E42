package br.edu.infnet.franklin.model.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
public class Embalagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;
    private Integer quantidadePorPacote;
    private BigDecimal precoPacote;

    @ManyToMany(mappedBy = "embalagens")
    private Set<Produto> produtos;

    // Construtores
    public Embalagem() {
    }

    // Getters e Setters
    public Long getId() { return id; }

    public String getDescricao() { return descricao; }

    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Integer getQuantidadePorPacote() { return quantidadePorPacote; }

    public void setQuantidadePorPacote(Integer quantidadePorPacote) { this.quantidadePorPacote = quantidadePorPacote; }

    public BigDecimal getPrecoPacote() { return precoPacote; }

    public void setPrecoPacote(BigDecimal precoPacote) { this.precoPacote = precoPacote; }

    public Set<Produto> getProdutos() { return produtos; }

    public void setProdutos(Set<Produto> produtos) { this.produtos = produtos; }

    // Método para calcular o preço por unidade
    public BigDecimal getPrecoPorUnidade() {
        return precoPacote.divide(new BigDecimal(quantidadePorPacote), BigDecimal.ROUND_HALF_UP);
    }
}
