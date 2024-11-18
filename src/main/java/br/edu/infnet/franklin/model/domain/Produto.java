package br.edu.infnet.franklin.model.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    private String modoPreparo;

    private boolean conservadoGelado;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private Set<ProdutoReceita> produtoReceitas = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "produto_ingrediente",
        joinColumns = @JoinColumn(name = "produto_id"),
        inverseJoinColumns = @JoinColumn(name = "ingrediente_id")
    )
    private Set<Ingrediente> ingredientes = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "produto_embalagem",
        joinColumns = @JoinColumn(name = "produto_id"),
        inverseJoinColumns = @JoinColumn(name = "embalagem_id")
    )
    private Set<Embalagem> embalagens = new HashSet<>();

    // Construtores
    public Produto() {}

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getModoPreparo() {
        return modoPreparo;
    }

    public void setModoPreparo(String modoPreparo) {
        this.modoPreparo = modoPreparo;
    }

    public boolean isConservadoGelado() {
        return conservadoGelado;
    }

    public void setConservadoGelado(boolean conservadoGelado) {
        this.conservadoGelado = conservadoGelado;
    }

    public Set<ProdutoReceita> getProdutoReceitas() {
        return produtoReceitas;
    }

    public void setProdutoReceitas(Set<ProdutoReceita> produtoReceitas) {
        this.produtoReceitas = produtoReceitas;
    }

    public Set<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(Set<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public Set<Embalagem> getEmbalagens() {
        return embalagens;
    }

    public void setEmbalagens(Set<Embalagem> embalagens) {
        this.embalagens = embalagens;
    }

    // Método para calcular o preço de custo total
    public BigDecimal getPrecoCustoTotal() {
        BigDecimal total = BigDecimal.ZERO;

        if (produtoReceitas != null) {
            for (ProdutoReceita pr : produtoReceitas) {
                total = total.add(pr.getReceita().getCustoTotal().multiply(BigDecimal.valueOf(pr.getQuantidade())));
            }
        }

        if (ingredientes != null) {
            for (Ingrediente ingrediente : ingredientes) {
                total = total.add(ingrediente.getPrecoTotal());
            }
        }

        if (embalagens != null) {
            for (Embalagem embalagem : embalagens) {
                total = total.add(embalagem.getPrecoPorUnidade());
            }
        }

        return total;
    }
}