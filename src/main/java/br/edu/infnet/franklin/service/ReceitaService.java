package br.edu.infnet.franklin.service;

import br.edu.infnet.franklin.model.domain.Ingrediente;
import br.edu.infnet.franklin.model.domain.Receita;
import br.edu.infnet.franklin.model.domain.ReceitaIngrediente;
import br.edu.infnet.franklin.repository.ReceitaRepository;
import br.edu.infnet.franklin.service.ReceitaIngredienteService;
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

    public void salvar(Receita receita, Map<Long, Double> ingredientesQuantidade) {
        // Salva a receita inicial para obter o ID
        receitaRepository.save(receita);

        // Adiciona os ingredientes e suas quantidades
        for (Map.Entry<Long, Double> entry : ingredientesQuantidade.entrySet()) {
            Long ingredienteId = entry.getKey();
            Double quantidade = entry.getValue();

            Ingrediente ingrediente = ingredienteService.obterPorId(ingredienteId);

            ReceitaIngrediente receitaIngrediente = new ReceitaIngrediente();
            receitaIngrediente.setReceita(receita);
            receitaIngrediente.setIngrediente(ingrediente);
            receitaIngrediente.setQuantidade(quantidade);

            receitaIngredienteService.salvar(receitaIngrediente);
        }
    }

    public List<Receita> obterLista() {
        return receitaRepository.findAll();
    }

    public Receita obterPorId(Long id)
