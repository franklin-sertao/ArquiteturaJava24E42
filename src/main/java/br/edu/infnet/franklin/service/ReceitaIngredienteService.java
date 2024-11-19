package br.edu.infnet.franklin.service;

import br.edu.infnet.franklin.model.domain.Receita;
import br.edu.infnet.franklin.model.domain.ReceitaIngrediente;
import br.edu.infnet.franklin.repository.ReceitaIngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceitaIngredienteService {

    @Autowired
    private ReceitaIngredienteRepository receitaIngredienteRepository;

    public ReceitaIngrediente salvar(ReceitaIngrediente receitaIngrediente) {
        return receitaIngredienteRepository.save(receitaIngrediente);
    }

    public void excluirPorReceita(Receita receita) {
        receitaIngredienteRepository.deleteByReceita(receita);
    }

    public List<ReceitaIngrediente> obterLista() {
        return receitaIngredienteRepository.findAll();
    }

    public ReceitaIngrediente obterPorId(Long id) {
        return receitaIngredienteRepository.findById(id).orElse(null);
    }

    public void excluir(Long id) {
        receitaIngredienteRepository.deleteById(id);
    }
}
