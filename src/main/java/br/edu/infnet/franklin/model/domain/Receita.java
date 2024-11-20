package br.edu.infnet.franklin.model.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Receita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String modoPreparo;

    @OneToMany(mappedBy = "receita", fetch = FetchType.EAGER,  cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<ReceitaIngrediente> receitaIngredientes = new ArrayList<>();

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getModoPreparo() {
        return modoPreparo;
    }

    public List<ReceitaIngrediente> getReceitaIngredientes() {
        return receitaIngredientes;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setModoPreparo(String modoPreparo) {
        this.modoPreparo = modoPreparo;
    }

    public void setReceitaIngredientes(List<ReceitaIngrediente> receitaIngredientes) {
        this.receitaIngredientes = receitaIngredientes;
    }
}
