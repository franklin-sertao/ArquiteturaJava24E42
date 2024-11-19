package br.edu.infnet.franklin.service;

import br.edu.infnet.franklin.model.domain.*;
import br.edu.infnet.franklin.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ReceitaService receitaService;

    @Autowired
    private IngredienteService ingredienteService;

    @Autowired
    private ProdutoReceitaService produtoReceitaService;

    @Autowired
    private ProdutoIngredienteService produtoIngredienteService;

    public void salvar(Produto produto, Map<Long, Double> receitasQuantidade, Map<Long, Double> ingredientesQuantidade) {
        // Salva o produto inicialmente para obter o ID
        produtoRepository.save(produto);

        // Adicionar receitas ao produto com suas quantidades
        for (Map.Entry<Long, Double> entry : receitasQuantidade.entrySet()) {
            Long receitaId = entry.getKey();
            Double quantidade = entry.getValue();

            Receita receita = receitaService.obterPorId(receitaId);

            ProdutoReceita pr = new ProdutoReceita();
            pr.setProduto(produto);
            pr.setReceita(receita);
            pr.setQuantidade(quantidade);

            produtoReceitaService.salvar(pr);
        }

        // Adicionar ingredientes ao produto com suas quantidades
        for (Map.Entry<Long, Double> entry : ingredientesQuantidade.entrySet()) {
            Long ingredienteId = entry.getKey();
            Double quantidade = entry.getValue();

            Ingrediente ingrediente = ingredienteService.obterPorId(ingredienteId);

            ProdutoIngrediente produtoIngrediente = new ProdutoIngrediente();
            produtoIngrediente.setProduto(produto);
            produtoIngrediente.setIngrediente(ingrediente);
            produtoIngrediente.setQuantidade(quantidade);

            produtoIngredienteService.salvar(produtoIngrediente);
        }

        // Salvar o produto novamente com as associações atualizadas
        produtoRepository.save(produto);
    }

    public List<Produto> obterLista() {
        return produtoRepository.findAll();
    }

    public Produto obterPorId(Long id) {
        return produtoRepository.findById(id).orElse(null);
    }

    public void excluir(Long id) {
        produtoRepository.deleteById(id);
    }
}
