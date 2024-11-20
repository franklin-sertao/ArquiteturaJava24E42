package br.edu.infnet.franklin.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infnet.franklin.model.domain.Ingrediente;
import br.edu.infnet.franklin.model.domain.Produto;
import br.edu.infnet.franklin.model.domain.ProdutoIngrediente;
import br.edu.infnet.franklin.model.domain.ProdutoReceita;
import br.edu.infnet.franklin.model.domain.Receita;
import br.edu.infnet.franklin.repository.ProdutoRepository;
import jakarta.transaction.Transactional;

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

	@Transactional
    public void salvar(Long id, Produto produto, Map<Long, Double> receitasQuantidade, Map<Long, Double> ingredientesQuantidade) {
        if (id != null && produtoRepository.existsById(id)) {
            Produto produtoExistente = produtoRepository.findById(id).orElse(null);
            if (produtoExistente != null) {
                // Remove associações antigas
                produtoExistente.getProdutoReceitas().clear();
                produtoExistente.getProdutoIngredientes().clear();

				// Atualiza os campos do produto existente
				produtoExistente.setDescricao(produto.getDescricao());
				produtoExistente.setModoPreparo(produto.getModoPreparo());
				produtoExistente.setConservadoGelado(produto.isConservadoGelado());

                // Adiciona novas associações de Receitas
                for (Map.Entry<Long, Double> entry : receitasQuantidade.entrySet()) {
                    Long receitaId = entry.getKey();
                    Double quantidade = entry.getValue();
                    Receita receita = receitaService.obterPorId(receitaId);
                    if (receita != null) {
                        ProdutoReceita produtoReceita = new ProdutoReceita();
                        produtoReceita.setProduto(produtoExistente);
                        produtoReceita.setReceita(receita);
                        produtoReceita.setQuantidade(quantidade);
                        produtoReceitaService.salvar(produtoReceita);
                    }
                }

                // Adiciona novas associações de Ingredientes
                for (Map.Entry<Long, Double> entry : ingredientesQuantidade.entrySet()) {
                    Long ingredienteId = entry.getKey();
                    Double quantidade = entry.getValue();
                    Ingrediente ingrediente = ingredienteService.obterPorId(ingredienteId);
                    if (ingrediente != null) {
                        ProdutoIngrediente produtoIngrediente = new ProdutoIngrediente();
                        produtoIngrediente.setProduto(produtoExistente);
                        produtoIngrediente.setIngrediente(ingrediente);
                        produtoIngrediente.setQuantidade(quantidade);
                        produtoIngredienteService.salvar(produtoIngrediente);
                    }
                }

                produtoRepository.save(produtoExistente);
            }
        } else {
            // Salva novo produto
            Produto novoProduto = produtoRepository.save(produto);

            // Adiciona associações de Receitas
            for (Map.Entry<Long, Double> entry : receitasQuantidade.entrySet()) {
                Long receitaId = entry.getKey();
                Double quantidade = entry.getValue();
                Receita receita = receitaService.obterPorId(receitaId);
                if (receita != null) {
                    ProdutoReceita produtoReceita = new ProdutoReceita();
                    produtoReceita.setProduto(novoProduto);
                    produtoReceita.setReceita(receita);
                    produtoReceita.setQuantidade(quantidade);
                    produtoReceitaService.salvar(produtoReceita);
                }
            }

            // Adiciona associações de Ingredientes
            for (Map.Entry<Long, Double> entry : ingredientesQuantidade.entrySet()) {
                Long ingredienteId = entry.getKey();
                Double quantidade = entry.getValue();
                Ingrediente ingrediente = ingredienteService.obterPorId(ingredienteId);
                if (ingrediente != null) {
                    ProdutoIngrediente produtoIngrediente = new ProdutoIngrediente();
                    produtoIngrediente.setProduto(novoProduto);
                    produtoIngrediente.setIngrediente(ingrediente);
                    produtoIngrediente.setQuantidade(quantidade);
                    produtoIngredienteService.salvar(produtoIngrediente);
                }
            }
        }
    }

    public void excluir(Long id) {
        if (id != null) {
            produtoReceitaService.excluirPorIdProduto(id);
            produtoIngredienteService.excluirPorIdProduto(id);
            produtoRepository.deleteById(id);
        }
    }

    public Produto obterPorId(Long id) {
        return produtoRepository.findById(id).orElse(null);
    }

    public Iterable<Produto> obterLista() {
        return produtoRepository.findAll();
    }
}
