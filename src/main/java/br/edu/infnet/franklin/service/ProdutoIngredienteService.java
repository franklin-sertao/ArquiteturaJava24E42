package br.edu.infnet.franklin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infnet.franklin.model.domain.ProdutoIngrediente;
import br.edu.infnet.franklin.repository.ProdutoIngredienteRepository;
import jakarta.transaction.Transactional;

@Service
public class ProdutoIngredienteService {

    @Autowired
    private ProdutoIngredienteRepository produtoIngredienteRepository;

	@Transactional
    public ProdutoIngrediente salvar(ProdutoIngrediente produtoIngrediente) {
        return produtoIngredienteRepository.save(produtoIngrediente);
    }

	@Transactional
    public void excluirPorIdProduto(Long id) {
		if(id == null) {
            throw new IllegalArgumentException("Produto não informado!");
		}
		
		produtoIngredienteRepository.deleteByProdutoId(id);
	}

    public Iterable<ProdutoIngrediente> obterLista() {
        return produtoIngredienteRepository.findAll();
    }

    public ProdutoIngrediente obterPorId(Long id) {
        return produtoIngredienteRepository.findById(id).orElse(null);
    }

	@Transactional
    public void excluir(Long id) {
        produtoIngredienteRepository.deleteById(id);
    }
}
