package br.edu.infnet.franklin.loader;

import br.edu.infnet.franklin.model.domain.Receita;
import br.edu.infnet.franklin.service.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Component
public class ReceitaLoader implements CommandLineRunner {

    @Autowired
    private ReceitaService receitaService;

    @Override
    public void run(String... args) throws Exception {
        carregarReceitas();
    }

    private void carregarReceitas() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                this.getClass().getResourceAsStream("/data/receitas.txt")));

        String line;
        while ((line = reader.readLine()) != null) {
            String[] campos = line.split(";", 4); // id,nome,modoPreparo,resto
            Long id = Long.parseLong(campos[0]);
            String nome = campos[1];
            String modoPreparo = campos[2];
            String resto = campos[3];

            Receita receita = new Receita();
            receita.setId(id);
            receita.setNome(nome);
            receita.setModoPreparo(modoPreparo);

            // Processa os ingredientes e quantidades
            String[] ingredientesCampos = resto.split(",");
            Map<Long, Double> ingredientesQuantidade = new HashMap<>();
            for (int i = 0; i < ingredientesCampos.length; i += 2) {
                if (i + 1 < ingredientesCampos.length) {
                    Long ingredienteId = Long.parseLong(ingredientesCampos[i]);
                    Double quantidade = Double.parseDouble(ingredientesCampos[i + 1]);
                    ingredientesQuantidade.put(ingredienteId, quantidade);
                }
            }

            receitaService.salvar(receita, ingredientesQuantidade);
        }

        reader.close();
    }
}
