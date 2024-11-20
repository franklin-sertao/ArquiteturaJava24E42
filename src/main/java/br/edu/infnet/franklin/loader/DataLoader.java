package br.edu.infnet.franklin.loader;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;


@Component
public class DataLoader implements CommandLineRunner {

    private final IngredienteLoader ingredienteLoader;
    private final EmbalagemLoader embalagemLoader;
    private final ReceitaLoader receitaLoader;
    private final ProdutoLoader produtoLoader;

    @Autowired
    public DataLoader(
        IngredienteLoader ingredienteLoader,
        EmbalagemLoader embalagemLoader,
        ReceitaLoader receitaLoader,
        ProdutoLoader produtoLoader
    ) {
        this.ingredienteLoader = ingredienteLoader;
        this.embalagemLoader = embalagemLoader;
        this.receitaLoader = receitaLoader;
        this.produtoLoader = produtoLoader;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Iniciando carregamento dos dados...");

        embalagemLoader.carregarEmbalagens();
        ingredienteLoader.carregarIngredientes();
        receitaLoader.carregarReceitas();
        produtoLoader.carregarProdutos();

        System.out.println("Carregamento concluído!");
    }
}
