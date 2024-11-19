package br.edu.infnet.franklin.service;

import br.edu.infnet.franklin.model.domain.Ingrediente;
import br.edu.infnet.franklin.model.domain.Receita;
import br.edu.infnet.franklin.model.domain.ReceitaIngrediente;
import br.edu.infnet.franklin.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.List;

@Service
public class ReceitaService {

    @Autowired
    private ReceitaRepository receitaRepository;

    @Autowired
    private IngredienteService ingredienteService;

    @Autowired
    private ReceitaIngredienteService receitaIngredienteService;

    public void salvar(Receita receita, Map<Long, Double> ingredientesQuantidade) {
        // Salva ou atualiza a receita
        receitaRepository.save(receita);
        
        // Remove associações antigas
        receitaIngredienteService.excluirPorReceita(receita);

        // Adiciona novas associações
        for (Map.Entry<Long, Double> entry : ingredientesQuantidade.entrySet()) {
            Long ingredienteId = entry.getKey();
            Double quantidade = entry.getValue();
            Ingrediente ingrediente = ingredienteService.obterPorId(ingredienteId);
            if (ingrediente != null) {
                ReceitaIngrediente receitaIngrediente = new ReceitaIngrediente();
                receitaIngrediente.setReceita(receita);
                receitaIngrediente.setIngrediente(ingrediente);
                receitaIngrediente.setQuantidade(quantidade);
                receitaIngredienteService.salvar(receitaIngrediente);
            }
        }
    }

    public List<Receita> obterLista() {
        return receitaRepository.findAll();
    }

    public Receita obterPorId(Long id) {
        return receitaRepository.findById(id).orElse(null);
    }

    public void excluir(Long id) {
        Receita receita = obterPorId(id);
        if (receita != null) {
            receitaIngredienteService.excluirPorReceita(receita);
            receitaRepository.deleteById(id);
        }
    }
}
