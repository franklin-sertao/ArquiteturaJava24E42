package br.edu.infnet.franklin.model.domain;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
public abstract class Ingrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private java.math.BigDecimal precoTotal;
    private boolean organico;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public java.math.BigDecimal getPrecoTotal() {
        return precoTotal;
    }

    public boolean isOrganico() {
        return organico;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPrecoTotal(java.math.BigDecimal precoTotal) {
        this.precoTotal = precoTotal;
    }

    public void setOrganico(boolean organico) {
        this.organico = organico;
    }

    public abstract String getTipo();
}
