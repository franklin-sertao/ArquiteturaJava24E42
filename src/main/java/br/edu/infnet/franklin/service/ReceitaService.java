package br.edu.infnet.franklin.service;

import br.edu.infnet.franklin.model.domain.Ingrediente;
import br.edu.infnet.franklin.model.domain.Receita;
import br.edu.infnet.franklin.model.domain.ReceitaIngrediente;
import br.edu.infnet.franklin.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceitaService {

    @Autowired
    private ReceitaRepository receitaRepository;

    @Autowired
    private IngredienteService ingredienteService;

    @Autowired
    private ReceitaIngredienteService receitaIngredienteService;

    public void incluir(Receita receita, List<Long> ingredientesIds, List<Double> quantidades) {
        receitaRepository.save(receita);

        for (int i = 0; i < ingredientesIds.size(); i++) {
            Long ingredienteId = ingredientesIds.get(i);
            Double quantidade = quantidades.get(i);

            Ingrediente ingrediente = ingredienteService.obterPorId(ingredienteId);

            ReceitaIngrediente ri = new ReceitaIngrediente();
            ri.setReceita(receita);
            ri.setIngrediente(ingrediente);
            ri.setQuantidade(quantidade);

            receitaIngredienteService.incluir(ri);
        }
    }

    public List<Receita> obterLista() {
        return receitaRepository.findAll();
    }

    public Receita obterPorId(Long id) {
        return receitaRepository.findById(id).orElse(null);
    }

    public void excluir(Long id) {
        receitaRepository.deleteById(id);
    }

    public Receita obterPorNome(String nome) {
        return receitaRepository.findByNome(nome);
    }
}
