package br.edu.infnet.franklin.model.domain;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;
    private String modoPreparo;
    private boolean conservadoGelado;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProdutoReceita> produtoReceitas = new ArrayList<>();

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProdutoIngrediente> produtoIngredientes = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name = "produto_embalagem",
        joinColumns = @JoinColumn(name = "produto_id"),
        inverseJoinColumns = @JoinColumn(name = "embalagem_id")
    )
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
