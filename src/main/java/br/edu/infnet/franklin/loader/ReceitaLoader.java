package br.edu.infnet.franklin.loader;

import br.edu.infnet.franklin.model.domain.*;
import br.edu.infnet.franklin.service.IngredienteService;
import br.edu.infnet.franklin.service.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class ReceitaLoader {

    @Autowired
    private ReceitaService receitaService;

    @Autowired
    private IngredienteService ingredienteService;

    @PostConstruct
    public void loadReceitas() {
        try {
            // Carregar as receitas
            BufferedReader receitaReader = new BufferedReader(new InputStreamReader(
                    getClass().getResourceAsStream("/data/Receitas.txt"), "UTF-8"));

            String line;
            while ((line = receitaReader.readLine()) != null) {
                String[] campos = line.split(";");
                Receita receita = new Receita();
                receita.setNome(campos[0]);
                receita.setModoPreparo(campos[1]);
                receitaService.obterLista().add(receita);
            }
            receitaReader.close();

            // Carregar os ingredientes das receitas
            BufferedReader receitaIngredienteReader = new BufferedReader(new InputStreamReader(
                    getClass().getResourceAsStream("/data/ReceitaIngredientes.txt"), "UTF-8"));

            String receitaNomeAtual = "";
            Receita receitaAtual = null;
            List<Long> ingredientesIds = new ArrayList<>();
            List<Double> quantidades = new ArrayList<>();

            while ((line = receitaIngredienteReader.readLine()) != null) {
                String[] campos = line.split(";");
                String receitaNome = campos[0];
                String ingredienteNome = campos[1];
                Double quantidade = Double.parseDouble(campos[2]);

                if (!receitaNome.equals(receitaNomeAtual)) {
                    if (receitaAtual != null) {
                        receitaService.incluir(receitaAtual, ingredientesIds, quantidades);
                        ingredientesIds.clear();
                        quantidades.clear();
                    }
                    receitaAtual = receitaService.obterPorNome(receitaNome);
                    receitaNomeAtual = receitaNome;
                }

                Ingrediente ingrediente = ingredienteService.obterPorNome(ingredienteNome);
                if (ingrediente != null) {
                    ingredientesIds.add(ingrediente.getId());
                    quantidades.add(quantidade);
                }
            }

            // Incluir a última receita
            if (receitaAtual != null && !ingredientesIds.isEmpty()) {
                receitaService.incluir(receitaAtual, ingredientesIds, quantidades);
            }

            receitaIngredienteReader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
