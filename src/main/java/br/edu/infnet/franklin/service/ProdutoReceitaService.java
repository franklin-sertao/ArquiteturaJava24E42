package br.edu.infnet.franklin.service;

import br.edu.infnet.franklin.model.domain.Produto;
import br.edu.infnet.franklin.model.domain.ProdutoReceita;
import br.edu.infnet.franklin.repository.ProdutoReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoReceitaService {

    @Autowired
    private ProdutoReceitaRepository produtoReceitaRepository;

    public ProdutoReceita salvar(ProdutoReceita produtoReceita) {
        return produtoReceitaRepository.save(produtoReceita);
    }

    public void excluirPorProduto(Produto produto) {
        produtoReceitaRepository.deleteByProduto(produto);
    }

    public List<ProdutoReceita> obterLista() {
        return produtoReceitaRepository.findAll();
    }

    public ProdutoReceita obterPorId(Long id) {
        return produtoReceitaRepository.findById(id).orElse(null);
    }

    public void excluir(Long id) {
        produtoReceitaRepository.deleteById(id);
    }
}
