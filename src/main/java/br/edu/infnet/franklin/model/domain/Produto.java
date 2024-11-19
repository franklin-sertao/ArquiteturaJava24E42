package br.edu.infnet.franklin.model.domain;

import jakarta.persistence.*;
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

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProdutoReceita> produtoReceitas = new HashSet<>();

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProdutoIngrediente> produtoIngredientes = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "produto_embalagem",
            joinColumns = @JoinColumn(name = "produto_id"),
            inverseJoinColumns = @JoinColumn(name = "embalagem_id")
    )
    private Set<Embalagem> embalagens = new HashSet<>();

    // Construtores
    public Produto() {
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) { this.id = id; }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getModoPreparo() { return modoPreparo; }

    public void setModoPreparo(String modoPreparo) { this.modoPreparo = modoPreparo; }

    public boolean isConservadoGelado() { return conservadoGelado; }

    public void setConservadoGelado(boolean conservadoGelado) { this.conservadoGelado = conservadoGelado; }

    public Set<ProdutoReceita> getProdutoReceitas() { return produtoReceitas; }

    public void setProdutoReceitas(Set<ProdutoReceita> produtoReceitas) { this.produtoReceitas = produtoReceitas; }

    public Set<ProdutoIngrediente> getProdutoIngredientes() { return produtoIngredientes; }

    public void setProdutoIngredientes(Set<ProdutoIngrediente> produtoIngredientes) { this.produtoIngredientes = produtoIngredientes; }

    public Set<Embalagem> getEmbalagens() { return embalagens; }

    public void setEmbalagens(Set<Embalagem> embalagens) { this.embalagens = embalagens; }

    // Método para calcular o preço de custo total
    public BigDecimal getPrecoCustoTotal() {
        BigDecimal total = BigDecimal.ZERO;

        if (produtoReceitas != null) {
            for (ProdutoReceita pr : produtoReceitas) {
                total = total.add(pr.getReceita().getCustoTotal().multiply(BigDecimal.valueOf(pr.getQuantidade())));
            }
        }

        if (produtoIngredientes != null) {
            for (ProdutoIngrediente pi : produtoIngredientes) {
                total = total.add(pi.getIngrediente().getPrecoPorUnidade().multiply(BigDecimal.valueOf(pi.getQuantidade())));
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
