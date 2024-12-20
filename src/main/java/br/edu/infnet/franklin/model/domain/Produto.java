package br.edu.infnet.franklin.model.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.ReadOnlyProperty;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.edu.infnet.franklin.service.ProdutoService;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	
	@NotBlank (message = "O campo descrição é obrigatório")
    private String descricao;
    private String modoPreparo;
    private boolean conservadoGelado;

    @OneToMany(mappedBy = "produto",  fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProdutoReceita> produtoReceitas = new ArrayList<>();

    @OneToMany(mappedBy = "produto",  fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProdutoIngrediente> produtoIngredientes = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name = "produto_embalagem",
        joinColumns = @JoinColumn(name = "idProduto"),
        inverseJoinColumns = @JoinColumn(name = "idEmbalagem")
    )
	@JsonManagedReference
    private List<Embalagem> embalagens = new ArrayList<>();

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getModoPreparo() {
        return modoPreparo;
    }

    public boolean isConservadoGelado() {
        return conservadoGelado;
    }

    public List<ProdutoReceita> getProdutoReceitas() {
        return produtoReceitas;
    }

    public List<ProdutoIngrediente> getProdutoIngredientes() {
        return produtoIngredientes;
    }

    public List<Embalagem> getEmbalagens() {
        return embalagens;
    }

	public Double getPrecoTotal() {		
		return ProdutoService.calcularPrecoTotal(this);
	}

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setModoPreparo(String modoPreparo) {
        this.modoPreparo = modoPreparo;
    }

    public void setConservadoGelado(boolean conservadoGelado) {
        this.conservadoGelado = conservadoGelado;
    }

    public void setProdutoReceitas(List<ProdutoReceita> produtoReceitas) {
        this.produtoReceitas = produtoReceitas;
    }

    public void setProdutoIngredientes(List<ProdutoIngrediente> produtoIngredientes) {
        this.produtoIngredientes = produtoIngredientes;
    }

    public void setEmbalagens(List<Embalagem> embalagens) {
        this.embalagens = embalagens;
    }
}
