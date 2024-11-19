package br.edu.infnet.franklin.model.dto;

public class IngredienteForm {

    private Long id;
    private String tipo;
    private String nome;
    private java.math.BigDecimal precoTotal;
    private boolean organico;

    // Campos específicos
    private Integer pesoLiquidoEmGramas;      // Para "seco"
    private Integer volumeLiquidoEmML;        // Para "liquido"
    private Integer quantidadeUnidades;       // Para "unitario"

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

    public java.math.BigDecimal getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(java.math.BigDecimal precoTotal) {
        this.precoTotal = precoTotal;
    }

    public boolean isOrganico() {
        return organico;
    }

    public void setOrganico(boolean organico) {
        this.organico = organico;
    }

    public Integer getPesoLiquidoEmGramas() {
        return pesoLiquidoEmGramas;
    }

    public void setPesoLiquidoEmGramas(Integer pesoLiquidoEmGramas) {
        this.pesoLiquidoEmGramas = pesoLiquidoEmGramas;
    }

    public Integer getVolumeLiquidoEmML() {
        return volumeLiquidoEmML;
    }

    public void setVolumeLiquidoEmML(Integer volumeLiquidoEmML) {
        this.volumeLiquidoEmML = volumeLiquidoEmML;
    }

    public Integer getQuantidadeUnidades() {
        return quantidadeUnidades;
    }

    public void setQuantidadeUnidades(Integer quantidadeUnidades) {
        this.quantidadeUnidades = quantidadeUnidades;
    }
}
