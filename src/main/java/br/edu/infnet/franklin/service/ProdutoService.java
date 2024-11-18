package br.edu.infnet.franklin.service;

import br.edu.infnet.franklin.model.domain.Produto;
import br.edu.infnet.franklin.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public void incluir(Produto produto) {
        produtoRepository.save(produto);
    }

    public List<Produto> obterLista() {
        return produtoRepository.findAll();
    }

    public void excluir(Long id) {
        produtoRepository.deleteById(id);
    }

    public Produto findByDescricao(String descricao) {
        return produtoRepository.findByDescricao(descricao);
    }
}
