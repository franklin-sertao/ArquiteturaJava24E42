package br.edu.infnet.franklin.model.dto;

public class IngredienteForm {

    private Long id;
    private String tipo;
    private String nome;
    private Double precoTotal;
    private boolean organico;

    // Campos específicos
    private Double pesoLiquidoEmGramas;      // Para "seco"
    private Double volumeLiquidoEmML;        // Para "liquido"
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
