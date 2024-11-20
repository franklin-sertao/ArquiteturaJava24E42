package br.edu.infnet.franklin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infnet.franklin.model.domain.ProdutoReceita;
import br.edu.infnet.franklin.repository.ProdutoReceitaRepository;
import jakarta.transaction.Transactional;

@Service
public class ProdutoReceitaService {

    @Autowired
    private ProdutoReceitaRepository produtoReceitaRepository;

    @Transactional
	public ProdutoReceita salvar(ProdutoReceita produtoReceita) {
        return produtoReceitaRepository.save(produtoReceita);
    }

	@Transactional
	public void excluirPorIdProduto(Long id) {
		if(id == null) {
            throw new IllegalArgumentException("Produto não informado!");
		}
		
		produtoReceitaRepository.deleteByProdutoId(id);
	}

    public Iterable<ProdutoReceita> obterLista() {
        return produtoReceitaRepository.findAll();
    }

    public ProdutoReceita obterPorId(Long id) {
        return produtoReceitaRepository.findById(id).orElse(null);
    }

	@Transactional
    public void excluir(Long id) {
        produtoReceitaRepository.deleteById(id);
    }
}
