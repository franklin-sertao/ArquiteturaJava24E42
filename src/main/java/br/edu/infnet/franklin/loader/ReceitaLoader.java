package br.edu.infnet.franklin.loader;

import br.edu.infnet.franklin.model.domain.*;
import br.edu.infnet.franklin.service.IngredienteService;
import br.edu.infnet.franklin.service.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Component
public class ReceitaLoader {

    @Autowired
    private ReceitaService receitaService;

    @Autowired
    private IngredienteService ingredienteService;

    @PostConstruct
    public void loadReceitas() {
        try {
            BufferedReader receitaReader = new BufferedReader(new InputStreamReader(
                    getClass().getResourceAsStream("/data/Receitas.txt"), "UTF-8"));

            String line;
            while ((line = receitaReader.readLine()) != null) {
                String[] campos = line.split(";");
                Receita receita = new Receita();
                receita.setNome(campos[0]);
                receita.setModoPreparo(campos[1]);

                // Map para ingredientes e quantidades
                Map<Long, Double> ingredientesQuantidade = new HashMap<>();

                // Carregar ingredientes da receita
                BufferedReader receitaIngredienteReader = new BufferedReader(new InputStreamReader(
                        getClass().getResourceAsStream("/data/ReceitaIngredientes.txt"), "UTF-8"));

                String riLine;
                while ((riLine = receitaIngredienteReader.readLine()) != null) {
                    String[] riCampos = riLine.split(";");
                    if (riCampos[0].equals(receita.getNome())) {
                        Ingrediente ingrediente = ingredienteService.obterPorNome(riCampos[1]);
                        Double quantidade = Double.parseDouble(riCampos[2]);
                        ingredientesQuantidade.put(ingrediente.getId(), quantidade);
                    }
                }
                receitaIngredienteReader.close();

                receitaService.salvar(receita, ingredientesQuantidade);
            }
            receitaReader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
