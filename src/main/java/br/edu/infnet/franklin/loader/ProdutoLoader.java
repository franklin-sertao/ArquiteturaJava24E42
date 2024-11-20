package br.edu.infnet.franklin.loader;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.infnet.franklin.model.domain.Produto;
import br.edu.infnet.franklin.service.ProdutoService;


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
			
			String[] campos = line.split(";", 5); // id, descricao, modoPreparo, conservadoGelado, resto
			
			Long id = Long.parseLong(campos[0]);
			String descricao = campos[1];
			
			String modoPreparo = campos[2];
			boolean conservadoGelado = Boolean.parseBoolean(campos[3]);
			String resto = campos[4];

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
	
			String[] elementos = resto.split("#", -1);
			if (elementos.length > 0 && !elementos[0].isEmpty()) {
				String[] receitasCampos = elementos[0].split(";", -1);
				for (int i = 0; i < receitasCampos.length; i += 2) {
					if (i + 1 < receitasCampos.length) {
						try {
							Long receitaId = Long.parseLong(receitasCampos[i]);
							Double quantidade = Double.parseDouble(receitasCampos[i + 1]);
							receitasQuantidade.put(receitaId, quantidade);
						} catch (NumberFormatException e) {
							System.err.println("Erro ao processar receitas: " + e.getMessage());
						}
					}
				}
			}
	
			if (elementos.length > 1 && !elementos[1].isEmpty()) {
				String[] ingredientesCampos = elementos[1].split(";", -1);
				for (int i = 0; i < ingredientesCampos.length; i += 2) {
					if (i + 1 < ingredientesCampos.length) {
						try {
							Long ingredienteId = Long.parseLong(ingredientesCampos[i]);
							Double quantidade = Double.parseDouble(ingredientesCampos[i + 1]);
							ingredientesQuantidade.put(ingredienteId, quantidade);
						} catch (NumberFormatException e) {
							System.err.println("Erro ao processar ingredientes: " + e.getMessage());
						}
					}
				}
			}
	
			produtoService.salvar(id, produto, receitasQuantidade, ingredientesQuantidade);
		}
	
		reader.close();
	}
}
