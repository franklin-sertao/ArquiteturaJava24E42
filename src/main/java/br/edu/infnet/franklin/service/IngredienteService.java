package br.edu.infnet.franklin.service;

import br.edu.infnet.franklin.model.domain.Ingrediente;
import br.edu.infnet.franklin.repository.IngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredienteService {

    @Autowired
    private IngredienteRepository ingredienteRepository;

    public void incluir(Ingrediente ingrediente) {
        ingredienteRepository.save(ingrediente);
    }

    public List<Ingrediente> obterLista() {
        return ingredienteRepository.findAll();
    }

    public Ingrediente obterPorId(Long id) {
        return ingredienteRepository.findById(id).orElse(null);
    }

    public void excluir(Long id) {
        ingredienteRepository.deleteById(id);
    }

    public Ingrediente obterPorNome(String nome) {
        return ingredienteRepository.findByNome(nome);
    }
}
