package br.edu.infnet.franklin.model.domain;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Embalagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;
    private Integer quantidadePorPacote;
    private Double precoPacote;

    @ManyToMany(mappedBy = "embalagens", fetch = FetchType.EAGER)
	@JsonBackReference
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

    public Double getPrecoPacote() {
        return precoPacote;
    }

	public Double getPrecoUnitario() {
		if (this.precoPacote != null) {
			return this.precoPacote / this.quantidadePorPacote.doubleValue();
		}
		return null;
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

    public void setPrecoPacote(Double precoPacote) {
        this.precoPacote = precoPacote;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}
