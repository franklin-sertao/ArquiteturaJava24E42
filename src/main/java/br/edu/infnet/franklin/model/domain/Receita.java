package br.edu.infnet.franklin.model.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Receita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String modoPreparo;

    @OneToMany(mappedBy = "receita", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ReceitaIngrediente> receitaIngredientes = new HashSet<>();

    @OneToMany(mappedBy = "receita", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProdutoReceita> produtoReceitas = new HashSet<>();

    // Construtores
    public Receita() {
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getModoPreparo() {
        return modoPreparo;
    }

    public void setModoPreparo(String modoPreparo) {
        this.modoPreparo = modoPreparo;
    }

    public Set<ReceitaIngrediente> getReceitaIngredientes() {
        return receitaIngredientes;
    }

    public void setReceitaIngredientes(Set<ReceitaIngrediente> receitaIngredientes) {
        this.receitaIngredientes = receitaIngredientes;
    }

    public Set<ProdutoReceita> getProdutoReceitas() {
        return produtoReceitas;
    }

    public void setProdutoReceitas(Set<ProdutoReceita> produtoReceitas) {
        this.produtoReceitas = produtoReceitas;
    }

    // Método para adicionar ReceitaIngrediente
    public void adicionarIngrediente(ReceitaIngrediente receitaIngrediente) {
        receitaIngredientes.add(receitaIngrediente);
        receitaIngrediente.setReceita(this);
    }

    // Método para calcular o custo total da receita
    public BigDecimal getCustoTotal() {
        BigDecimal total = BigDecimal.ZERO;
        if (receitaIngredientes != null) {
            for (ReceitaIngrediente ri : receitaIngredientes) {
                total = total.add(ri.getIngrediente().getPrecoPorUnidade().multiply(BigDecimal.valueOf(ri.getQuantidade())));
            }
        }
        return total;
    }
}
