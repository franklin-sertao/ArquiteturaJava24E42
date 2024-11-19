package br.edu.infnet.franklin.service;

import br.edu.infnet.franklin.model.domain.ProdutoIngrediente;
import br.edu.infnet.franklin.repository.ProdutoIngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoIngredienteService {

    @Autowired
    private ProdutoIngredienteRepository produtoIngredienteRepository;

    public void salvar(ProdutoIngrediente produtoIngrediente) {
        produtoIngredienteRepository.save(produtoIngrediente);
    }

    public List<ProdutoIngrediente> obterLista() {
        return produtoIngredienteRepository.findAll();
    }

    public ProdutoIngrediente obterPorId(Long id) {
        return produtoIngredienteRepository.findById(id).orElse(null);
    }

    public void excluir(Long id) {
        produtoIngredienteRepository.deleteById(id);
    }
}
