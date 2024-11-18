package br.edu.infnet.franklin.service;

import br.edu.infnet.franklin.model.domain.ReceitaIngrediente;
import br.edu.infnet.franklin.repository.ReceitaIngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceitaIngredienteService {

    @Autowired
    private ReceitaIngredienteRepository receitaIngredienteRepository;

    public void incluir(ReceitaIngrediente receitaIngrediente) {
        receitaIngredienteRepository.save(receitaIngrediente);
    }

    public List<ReceitaIngrediente> obterLista() {
        return receitaIngredienteRepository.findAll();
    }

    public void excluir(Long id) {
        receitaIngredienteRepository.deleteById(id);
    }
}
