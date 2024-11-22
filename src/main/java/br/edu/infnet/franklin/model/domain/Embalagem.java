package br.edu.infnet.franklin.model.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Embalagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@NotBlank (message = "O campo descrição é obrigatório")
    private String descricao;

	@NotNull (message = "O campo quantidade por pacote é obrigatório")
	@Positive (message = "O campo quantidade por pacote deve ser maior que zero")
    private Integer quantidadePorPacote;

	@NotNull (message = "O campo preço do pacote é obrigatório")
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
