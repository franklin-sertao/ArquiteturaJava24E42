package br.edu.infnet.franklin.service;

import br.edu.infnet.franklin.model.domain.Ingrediente;
import br.edu.infnet.franklin.model.domain.Receita;
import br.edu.infnet.franklin.model.domain.ReceitaIngrediente;
import br.edu.infnet.franklin.repository.ReceitaRepository;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReceitaService {

    @Autowired
    private ReceitaRepository receitaRepository;

    @Autowired
    private IngredienteService ingredienteService;

    @Autowired
    private ReceitaIngredienteService receitaIngredienteService;

	@Transactional
    public Receita salvar(Receita receita, Map<Long, Double> ingredientesQuantidade) {
        // Salva ou atualiza a receita
        Receita receitaSalva = receitaRepository.save(receita);

        // Remove associações antigas
        receitaIngredienteService.excluirPorReceita(receitaSalva);

        // Adiciona novas associações
        for (Map.Entry<Long, Double> entry : ingredientesQuantidade.entrySet()) {
            Long ingredienteId = entry.getKey();
            Double quantidade = entry.getValue();
            Ingrediente ingrediente = ingredienteService.obterPorId(ingredienteId);
            if (ingrediente != null) {
                ReceitaIngrediente receitaIngrediente = new ReceitaIngrediente();
                receitaIngrediente.setReceita(receitaSalva);
                receitaIngrediente.setIngrediente(ingrediente);
                receitaIngrediente.setQuantidade(quantidade);
                receitaIngredienteService.salvar(receitaIngrediente);
            }
        }

        return receitaSalva;
    }

    public void excluir(Long id) {
        Receita receita = obterPorId(id);
        if (receita != null) {
            receitaIngredienteService.excluirPorReceita(receita);
            receitaRepository.deleteById(id);
        }
    }

    public Receita obterPorId(Long id) {
        return receitaRepository.findById(id).orElse(null);
    }

    public Iterable<Receita> obterLista() {
        return receitaRepository.findAll();
    }

	//Obter preço total da receita
	public Double obterPrecoTotal(Receita receita) {
		Double precoTotal = 0.0;
		for (ReceitaIngrediente receitaIngrediente : receita.getReceitaIngredientes()) {
			precoTotal = precoTotal + receitaIngrediente.getIngrediente().getPrecoUnitario() * receitaIngrediente.getQuantidade();
		}
		return precoTotal;
	}

}