package br.edu.infnet.franklin.loader;

import br.edu.infnet.franklin.model.domain.*;
import br.edu.infnet.franklin.service.IngredienteService;
import br.edu.infnet.franklin.service.ReceitaService;
import br.edu.infnet.franklin.service.ReceitaIngredienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component
public class ReceitaLoader {

    @Autowired
    private ReceitaService receitaService;

    @Autowired
    private IngredienteService ingredienteService;

    @Autowired
    private ReceitaIngredienteService receitaIngredienteService;

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
                receitaService.incluir(receita);
            }
            receitaReader.close();

            // Carregar os ingredientes das receitas
            BufferedReader receitaIngredienteReader = new BufferedReader(new InputStreamReader(
                    getClass().getResourceAsStream("/data/ReceitaIngredientes.txt"), "UTF-8"));

            while ((line = receitaIngredienteReader.readLine()) != null) {
                String[] campos = line.split(";");
                String receitaNome = campos[0];
                String ingredienteNome = campos[1];
                Double quantidade = Double.parseDouble(campos[2]);

                Receita receita = receitaService.findByNome(receitaNome);
                Ingrediente ingrediente = ingredienteService.findByNome(ingredienteNome);

                if (receita != null && ingrediente != null) {
                    ReceitaIngrediente receitaIngrediente = new ReceitaIngrediente();
                    receitaIngrediente.setReceita(receita);
                    receitaIngrediente.setIngrediente(ingrediente);
                    receitaIngrediente.setQuantidade(quantidade);
                    receitaIngredienteService.incluir(receitaIngrediente);
                }
            }
            receitaIngredienteReader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
