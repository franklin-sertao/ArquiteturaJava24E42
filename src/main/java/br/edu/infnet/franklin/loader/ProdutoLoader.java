package br.edu.infnet.franklin.loader;

import br.edu.infnet.franklin.model.domain.*;
import br.edu.infnet.franklin.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

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
            BufferedReader produtoReader = new BufferedReader(new InputStreamReader(
                    getClass().getResourceAsStream("/data/Produtos.txt"), "UTF-8"));

            String line;
            while ((line = produtoReader.readLine()) != null) {
                String[] campos = line.split(";");
                Produto produto = new Produto();
                produto.setDescricao(campos[0]);
                produto.setModoPreparo(campos[1]);
                produto.setConservadoGelado(Boolean.parseBoolean(campos[2]));

                // Mapas para receitas e ingredientes com suas quantidades
                Map<Long, Double> receitasQuantidade = new HashMap<>();
                Map<Long, Double> ingredientesQuantidade = new HashMap<>();

                // Carregar receitas relacionadas ao produto
                BufferedReader produtoReceitaReader = new BufferedReader(new InputStreamReader(
                        getClass().getResourceAsStream("/data/ProdutoReceitas.txt"), "UTF-8"));

                String prLine;
                while ((prLine = produtoReceitaReader.readLine()) != null) {
                    String[] prCampos = prLine.split(";");
                    if (prCampos[0].equals(produto.getDescricao())) {
                        Receita receita = receitaService.obterPorNome(prCampos[1]);
                        Double quantidade = Double.parseDouble(prCampos[2]);
                        receitasQuantidade.put(receita.getId(), quantidade);
                    }
                }
                produtoReceitaReader.close();

                // Carregar ingredientes relacionados ao produto
                BufferedReader produtoIngredienteReader = new BufferedReader(new InputStreamReader(
                        getClass().getResourceAsStream("/data/ProdutoIngredientes.txt"), "UTF-8"));

                String piLine;
                while ((piLine = produtoIngredienteReader.readLine()) != null) {
                    String[] piCampos = piLine.split(";");
                    if (piCampos[0].equals(produto.getDescricao())) {
                        Ingrediente ingrediente = ingredienteService.obterPorNome(piCampos[1]);
                        Double quantidade = Double.parseDouble(piCampos[2]);
                        ingredientesQuantidade.put(ingrediente.getId(), quantidade);
                    }
                }
                produtoIngredienteReader.close();

                // Carregar embalagens relacionadas ao produto
                BufferedReader produtoEmbalagemReader = new BufferedReader(new InputStreamReader(
                        getClass().getResourceAsStream("/data/ProdutoEmbalagens.txt"), "UTF-8"));

                String peLine;
                Set<Embalagem> embalagens = new HashSet<>();
                while ((peLine = produtoEmbalagemReader.readLine()) != null) {
                    String[] peCampos = peLine.split(";");
                    if (peCampos[0].equals(produto.getDescricao())) {
                        Embalagem embalagem = embalagemService.obterPorDescricao(peCampos[1]);
                        embalagens.add(embalagem);
                    }
                }
                produtoEmbalagemReader.close();

                produto.setEmbalagens(embalagens);

                // Salvar o produto com receitas e ingredientes
                produtoService.salvar(produto, receitasQuantidade, ingredientesQuantidade);
            }
            produtoReader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
