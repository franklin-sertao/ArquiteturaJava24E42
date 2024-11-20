package br.edu.infnet.franklin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infnet.franklin.model.domain.Ingrediente;
import br.edu.infnet.franklin.repository.IngredienteRepository;

@Service
public class IngredienteService {

	@Autowired
	private IngredienteRepository ingredienteRepository;

    public Ingrediente salvar(Ingrediente ingrediente) {
        return ingredienteRepository.save(ingrediente);
    }

    public Iterable<Ingrediente> obterLista() {
        return ingredienteRepository.findAll();
    }

    public Ingrediente obterPorId(Long id) {
        return ingredienteRepository.findById(id).orElse(null);
    }

    public void excluir(Long id) {
        ingredienteRepository.deleteById(id);
    }
}
