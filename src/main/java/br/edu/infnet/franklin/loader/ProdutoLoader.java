package br.edu.infnet.franklin.loader;

import br.edu.infnet.franklin.model.domain.Produto;
import br.edu.infnet.franklin.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Component
public class ProdutoLoader implements CommandLineRunner {

    @Autowired
    private ProdutoService produtoService;

    @Override
    public void run(String... args) throws Exception {
        carregarProdutos();
    }

    private void carregarProdutos() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                this.getClass().getResourceAsStream("/data/produtos.txt")));

        String line;
        while ((line = reader.readLine()) != null) {
            String[] campos = line.split(";", 5); // id,descricao,modoPreparo,conservadoGelado,resto
            Long id = Long.parseLong(campos[0]);
            String descricao = campos[1];
            String modoPreparo = campos[2];
            boolean conservadoGelado = Boolean.parseBoolean(campos[3]);
            String resto = campos[4];

            Produto produto = new Produto();
            produto.setId(id);
            produto.setDescricao(descricao);
            produto.setModoPreparo(modoPreparo);
            produto.setConservadoGelado(conservadoGelado);

            // Processa receitas e ingredientes
            String[] elementos = resto.split(",");
            Map<Long, Double> receitasQuantidade = new HashMap<>();
            Map<Long, Double> ingredientesQuantidade = new HashMap<>();

            // Assumindo que as receitas e ingredientes estão divididos igualmente
            int meio = elementos.length / 2;
            for (int i = 0; i < meio; i += 2) {
                if (i + 1 < meio) {
                    Long receitaId = Long.parseLong(elementos[i]);
                    Double quantidade = Double.parseDouble(elementos[i + 1]);
                    receitasQuantidade.put(receitaId, quantidade);
                }
            }

            for (int i = meio; i < elementos.length; i += 2) {
                if (i + 1 < elementos.length) {
                    Long ingredienteId = Long.parseLong(elementos[i]);
                    Double quantidade = Double.parseDouble(elementos[i + 1]);
                    ingredientesQuantidade.put(ingredienteId, quantidade);
                }
            }

            produtoService.salvar(id, produto, receitasQuantidade, ingredientesQuantidade);
        }

        reader.close();
    }
}
