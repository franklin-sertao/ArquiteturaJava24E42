package br.edu.infnet.franklin.service;

import br.edu.infnet.franklin.model.domain.*;
import br.edu.infnet.franklin.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void incluir(Produto produto, List<Long> receitasIds, List<Double> quantidadesReceitas, List<Long> ingredientesIds) {
        produtoRepository.save(produto);

        // Adicionar receitas ao produto
        for (int i = 0; i < receitasIds.size(); i++) {
            Long receitaId = receitasIds.get(i);
            Double quantidade = quantidadesReceitas.get(i);

            Receita receita = receitaService.obterPorId(receitaId);

            ProdutoReceita pr = new ProdutoReceita();
            pr.setProduto(produto);
            pr.setReceita(receita);
            pr.setQuantidade(quantidade);

            produtoReceitaService.incluir(pr);
        }

        // Adicionar ingredientes ao produto
        for (Long ingredienteId : ingredientesIds) {
            Ingrediente ingrediente = ingredienteService.obterPorId(ingredienteId);
            produto.getIngredientes().add(ingrediente);
        }

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

    public Produto findByDescricao(String descricao) {
        return produtoRepository.findByDescricao(descricao);
    }
}
