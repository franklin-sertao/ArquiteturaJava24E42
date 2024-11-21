package br.edu.infnet.franklin.loader;

import br.edu.infnet.franklin.Constantes;
import br.edu.infnet.franklin.model.domain.Produto;
import br.edu.infnet.franklin.service.ProdutoService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class ProdutoLoader {

	@Autowired
    private ProdutoService produtoService;

	public void carregarProdutos() throws Exception {

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				this.getClass().getResourceAsStream("/data/produtos.txt")));
	
		String line;

		System.out.println("Carregando produtos...");

		while ((line = reader.readLine()) != null) {
			

			String[] items = line.split("#", Constantes.SPLIT_PERMITIR_VAZIO);

			String[] camposProduto      = items[0].split(";", Constantes.SPLIT_PERMITIR_VAZIO);
			String[] camposReceitas     = items[1].split(";", Constantes.SPLIT_PERMITIR_VAZIO);
			String[] camposIngredientes = items[2].split(";", Constantes.SPLIT_PERMITIR_VAZIO);
			String[] stringsEmbalagens  = items[3].split(";", Constantes.SPLIT_PERMITIR_VAZIO);
			
			Long id = Long.parseLong(camposProduto[0]);
			String descricao = camposProduto[1];
			
			String modoPreparo = camposProduto[2];
			boolean conservadoGelado = Boolean.parseBoolean(camposProduto[3]);

			if(produtoService.obterPorId(id) != null) {
				System.out.println("Produto " + descricao + " já cadastrado.");
				continue;
			}
			
			Produto produto = new Produto();
			produto.setId(id);
			produto.setDescricao(descricao);
			produto.setModoPreparo(modoPreparo);
			produto.setConservadoGelado(conservadoGelado);
			
			System.out.println("Carregando produto " + produto.getDescricao() + "...");
	
			// Processa receitas e ingredientes
			Map<Long, Double> receitasQuantidade = new HashMap<>();
			Map<Long, Double> ingredientesQuantidade = new HashMap<>();
	
			// Cria a relação entre produto e receitas
			if(camposReceitas.length > 0) {
				for (int i = 0; i < camposReceitas.length; i += 2) {
					if (i + 1 < camposReceitas.length) {
						try {
							Long receitaId = Long.parseLong(camposReceitas[i]);
							Double quantidade = Double.parseDouble(camposReceitas[i + 1]);
							receitasQuantidade.put(receitaId, quantidade);
						} catch (NumberFormatException e) {
							System.err.println("Erro ao processar receitas: " + e.getMessage());
						}
					}
				}
			}
			
			// Cria a relação entre produto e ingredientes
			if(camposIngredientes.length > 0) {
				for (int i = 0; i < camposIngredientes.length; i += 2) {
					if (i + 1 < camposIngredientes.length) {
						try {
							Long ingredienteId = Long.parseLong(camposIngredientes[i]);
							Double quantidade = Double.parseDouble(camposIngredientes[i + 1]);
							ingredientesQuantidade.put(ingredienteId, quantidade);
						} catch (NumberFormatException e) {
							System.err.println("Erro ao processar ingredientes: " + e.getMessage());
						}
					}
				}
			}

			List<Long> embalagens = new ArrayList<>();

			// Converte a lista de ids de embalagem de String para Long
			if(stringsEmbalagens.length > 0) {
				for (String embalagemId : stringsEmbalagens) {
					try {
						embalagens.add(Long.parseLong(embalagemId));
					} catch (NumberFormatException e) {
						System.err.println("Erro ao processar embalagens: " + e.getMessage());
					}
				}
			}


	
			produtoService.salvar(id, produto, receitasQuantidade, ingredientesQuantidade, embalagens);
		}
	
		reader.close();
	}
}
