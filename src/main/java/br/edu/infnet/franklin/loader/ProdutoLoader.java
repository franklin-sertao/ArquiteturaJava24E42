package br.edu.infnet.franklin.loader;

import br.edu.infnet.franklin.model.domain.*;
import br.edu.infnet.franklin.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProdutoLoader {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ReceitaService receitaService;

    @Autowired
    private IngredienteService ingredienteService;

    @Autowired
    private EmbalagemService embalagemService;

    @PostConstruct
    public void loadProdutos() {
        try {
            // Carregar os produtos
            BufferedReader produtoReader = new BufferedReader(new InputStreamReader(
                    getClass().getResourceAsStream("/data/Produtos.txt"), "UTF-8"));

            String line;
            while ((line = produtoReader.readLine()) != null) {
                String[] campos = line.split(";");
                Produto produto = new Produto();
                produto.setDescricao(campos[0]);
                produto.setModoPreparo(campos[1]);
                produto.setConservadoGelado(Boolean.parseBoolean(campos[2]));
                produtoService.incluir(produto);
            }
            produtoReader.close();

            // Associar receitas aos produtos
            BufferedReader produtoReceitaReader = new BufferedReader(new InputStreamReader(
                    getClass().getResourceAsStream("/data/ProdutoReceitas.txt"), "UTF-8"));

            while ((line = produtoReceitaReader.readLine()) != null) {
                String[] campos = line.split(";");
                String produtoDescricao = campos[0];
                String receitaNome = campos[1];
                Double quantidade = Double.parseDouble(campos[2]);

                Produto produto = produtoService.findByDescricao(produtoDescricao);
                Receita receita = receitaService.obterPorNome(receitaNome);

                if (produto != null && receita != null) {
                    ProdutoReceita produtoReceita = new ProdutoReceita();
                    produtoReceita.setProduto(produto);
                    produtoReceita.setReceita(receita);
                    produtoReceita.setQuantidade(quantidade);
                    produto.getProdutoReceitas().add(produtoReceita);
                    produtoService.incluir(produto);
                }
            }
            produtoReceitaReader.close();

            // Associar ingredientes aos produtos
            BufferedReader produtoIngredienteReader = new BufferedReader(new InputStreamReader(
                    getClass().getResourceAsStream("/data/ProdutoIngredientes.txt"), "UTF-8"));

            while ((line = produtoIngredienteReader.readLine()) != null) {
                String[] campos = line.split(";");
                String produtoDescricao = campos[0];
                String ingredienteNome = campos[1];

                Produto produto = produtoService.findByDescricao(produtoDescricao);
                Ingrediente ingrediente = ingredienteService.obterPorNome(ingredienteNome);

                if (produto != null && ingrediente != null) {
                    produto.getIngredientes().add(ingrediente);
                    produtoService.incluir(produto);
                }
            }
            produtoIngredienteReader.close();

            // Associar embalagens aos produtos
            BufferedReader produtoEmbalagemReader = new BufferedReader(new InputStreamReader(
                    getClass().getResourceAsStream("/data/ProdutoEmbalagens.txt"), "UTF-8"));

            while ((line = produtoEmbalagemReader.readLine()) != null) {
                String[] campos = line.split(";");
                String produtoDescricao = campos[0];
                String embalagemDescricao = campos[1];

                Produto produto = produtoService.findByDescricao(produtoDescricao);
                Embalagem embalagem = embalagemService.findByDescricao(embalagemDescricao);

                if (produto != null && embalagem != null) {
                    produto.getEmbalagens().add(embalagem);
                    produtoService.incluir(produto);
                }
            }
            produtoEmbalagemReader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
