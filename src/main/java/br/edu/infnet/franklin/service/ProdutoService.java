package br.edu.infnet.franklin.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infnet.franklin.model.domain.Embalagem;
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

	@Autowired
	private EmbalagemService embalagemService;

	@Transactional
    public void salvar(Long id, Produto produto, Map<Long, Double> receitasQuantidade, Map<Long, Double> ingredientesQuantidade, List<Long> embalagens) {
        
		Produto produtoExistente = produtoRepository.findById(id).orElse(null);
		
		if (id != null || produtoExistente == null) {
			produtoExistente = produtoRepository.save(produto);
		}

		if (produtoExistente != null) {
			// Remove associações antigas
			produtoExistente.getProdutoReceitas().clear();
			produtoExistente.getProdutoIngredientes().clear();
			produtoExistente.getEmbalagens().clear();
		}


		// Atualiza os campos do produto existente
		produtoExistente.setDescricao(produto.getDescricao());
		produtoExistente.setModoPreparo(produto.getModoPreparo());
		produtoExistente.setConservadoGelado(produto.isConservadoGelado());

		// Adiciona novas associações de Receitas
		if(receitasQuantidade != null) {
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
		}

		// Adiciona novas associações de Ingredientes
		if(ingredientesQuantidade != null) {
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
		}

		// Adiciona novas associações de Embalagens
		if(embalagens != null) {
			for (Long embalagemId : embalagens) {
				Embalagem embalagem = embalagemService.obterPorId(embalagemId);
				if (embalagem != null) {
					produtoExistente.getEmbalagens().add(embalagem);
				}
			}
		}

		produtoRepository.save(produtoExistente);
        
        
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
