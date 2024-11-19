package br.edu.infnet.franklin.loader;

import br.edu.infnet.franklin.model.domain.*;
import br.edu.infnet.franklin.service.ProdutoService;
import br.edu.infnet.franklin.service.ReceitaService;
import br.edu.infnet.franklin.service.IngredienteService;
import br.edu.infnet.franklin.service.EmbalagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

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

                // Mapas para receitas e ingredientes com suas quantidades
                Map<Long, Double> receitasQuantidade = new HashMap<>();
                Map<Long, Double> ingredientesQuantidade = new HashMap<>();

                // Carregar receitas relacionadas ao produto
                BufferedReader produtoReceitaReader = new BufferedReader(new InputStreamReader(
                        getClass().getResourceAsStream("/data/ProdutoReceitas.txt"), "UTF-8"));

                while ((line = produtoReceitaReader.readLine()) != null) {
                    String[] receitaCampos = line.split(";");
                    if (receitaCampos[0].equals(produto.getDescricao())) {
                        Long receitaId = receitaService.obterPorNome(receitaCampos[1]).getId();
                        Double quantidade = Double.parseDouble(receitaCampos[2]);
                        receitasQuantidade.put(receitaId, quantidade);
                    }
                }
                produtoReceitaReader.close();

                // Carregar ingredientes relacionados ao produto
                BufferedReader produtoIngredienteReader = new BufferedReader(new InputStreamReader(
                        getClass().getResourceAsStream("/data/ProdutoIngredientes.txt"), "UTF-8"));

                while ((line = produtoIngredienteReader.readLine()) != null) {
                    String[] ingredienteCampos = line.split(";");
                    if (ingredienteCampos[0].equals(produto.getDescricao())) {
                        Long ingredienteId = ingredienteService.obterPorNome(ingredienteCampos[1]).getId();
                        Double quantidade = Double.parseDouble(ingredienteCampos[2]);
                        ingredientesQuantidade.put(ingredienteId, quantidade);
                    }
                }
                produtoIngredienteReader.close();

                // Salvar o produto com receitas e ingredientes
                produtoService.salvar(produto, receitasQuantidade, ingredientesQuantidade);
            }
            produtoReader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
