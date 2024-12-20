package br.edu.infnet.franklin.model.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import com.fasterxml.jackson.annotation.JsonIdentityReference;

import br.edu.infnet.franklin.service.ProdutoReceitaService;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
public class Receita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@NotBlank (message = "O campo nome é obrigatório")
    private String nome;
    private String modoPreparo;

	@NotNull (message = "O campo rendimento é obrigatório")
	@Positive (message = "O campo rendimento deve ser maior que zero")
	private Double rendimento;

	@NotBlank (message = "O campo tipo de rendimento é obrigatório")
	private String tipoRendimento;

    @OneToMany(mappedBy = "receita", fetch = FetchType.EAGER,  cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIdentityReference
    private List<ReceitaIngrediente> receitaIngredientes = new ArrayList<>();
	// Metodos
	public Double getPrecoTotal() {
		Double total = 0.0;
		
		for (ReceitaIngrediente receitaIngrediente : receitaIngredientes) {
			total += receitaIngrediente.getIngrediente().getPrecoUnitario() * receitaIngrediente.getQuantidade();
		}

		return total;
	}

	public Double getPrecoUnitario() {
		Double precoTotal = getPrecoTotal();
		Double rendimento = getRendimento();

		if(precoTotal == null || rendimento == null) {
			return null;
		}

		if (precoTotal == 0 || rendimento == 0) {
			return 0.0;
		}

		return precoTotal / rendimento;
	}

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

	public Double getRendimento() {
		return rendimento;
	}

	public void setRendimento(Double rendimento) {
		this.rendimento = rendimento;
	}

	public String getTipoRendimento() {
		return tipoRendimento;
	}

	public void setTipoRendimento(String tipoRendimento) {
		this.tipoRendimento = tipoRendimento;
	}
	
}
