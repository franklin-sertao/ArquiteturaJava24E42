package br.edu.infnet.franklin.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public class IngredienteForm {

    private Long id;
	
	@NotBlank (message = "O campo tipo é obrigatório")
	private String tipo;

	@NotBlank (message = "O campo nome é obrigatório")
    private String nome;

	@NotNull (message = "O campo preço total é obrigatório")
	@PositiveOrZero (message = "O campo preço total deve ser maior ou igual a zero")
    private Double precoTotal;

    private boolean organico;

    // Campos específicos
	@PositiveOrZero (message = "O campo Peso (g) deve ser maior ou igual a zero")
    private Double pesoLiquidoEmGramas;      // Para "seco"
	@PositiveOrZero (message = "O campo Volume(ml) deve ser maior ou igual a zero")
    private Double volumeLiquidoEmML;        // Para "liquido"
	@PositiveOrZero (message = "O campo Quantidade deve ser maior ou igual a zero")
    private Integer quantidadeUnidades;      // Para "unitario"


    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(Double precoTotal) {
        this.precoTotal = precoTotal;
    }

    public boolean isOrganico() {
        return organico;
    }

    public void setOrganico(boolean organico) {
        this.organico = organico;
    }

    public Double getPesoLiquidoEmGramas() {
        return pesoLiquidoEmGramas;
    }

    public void setPesoLiquidoEmGramas(Double pesoLiquidoEmGramas) {
        this.pesoLiquidoEmGramas = pesoLiquidoEmGramas;
    }

    public Double getVolumeLiquidoEmML() {
        return volumeLiquidoEmML;
    }

    public void setVolumeLiquidoEmML(Double volumeLiquidoEmML) {
        this.volumeLiquidoEmML = volumeLiquidoEmML;
    }

    public Integer getQuantidadeUnidades() {
        return quantidadeUnidades;
    }

    public void setQuantidadeUnidades(Integer quantidadeUnidades) {
        this.quantidadeUnidades = quantidadeUnidades;
    }
}
