package br.edu.infnet.franklin.loader;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.infnet.franklin.model.domain.Receita;
import br.edu.infnet.franklin.service.ReceitaService;

@Component
public class ReceitaLoader{

	@Autowired
    private ReceitaService receitaService;

    public void carregarReceitas() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                this.getClass().getResourceAsStream("/data/receitas.txt")));

        String line;
        while ((line = reader.readLine()) != null) {
            String[] campos = line.split(";", 6); // id,nome,modoPreparo,resto
            Long id = Long.parseLong(campos[0]);
			String nome = campos[1];
            String modoPreparo = campos[2];
			Double rendimento = Double.parseDouble(campos[3]);
			String tipoRendimento = campos[4];
            String resto = campos[5];

			if(receitaService.obterPorId(id) != null) {
				System.out.println("Receita " + nome + " já cadastrada.");
				continue;
			}
			
			System.out.println("Carregando receita " + nome + "...");

            Receita receita = new Receita();
            receita.setId(id);
            receita.setNome(nome);
            receita.setModoPreparo(modoPreparo);
			receita.setRendimento(rendimento);
			receita.setTipoRendimento(tipoRendimento);

            // Processa os ingredientes e quantidades
            String[] ingredientesCampos = resto.split(";");
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
