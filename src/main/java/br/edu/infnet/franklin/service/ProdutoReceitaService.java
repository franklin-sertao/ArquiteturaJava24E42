package br.edu.infnet.franklin.service;

import br.edu.infnet.franklin.model.domain.ProdutoReceita;
import br.edu.infnet.franklin.repository.ProdutoReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoReceitaService {

    @Autowired
    private ProdutoReceitaRepository produtoReceitaRepository;

    public void incluir(ProdutoReceita produtoReceita) {
        produtoReceitaRepository.save(produtoReceita);
    }

    public List<ProdutoReceita> obterLista() {
        return produtoReceitaRepository.findAll();
    }

    public void excluir(Long id) {
        produtoReceitaRepository.deleteById(id);
    }
}