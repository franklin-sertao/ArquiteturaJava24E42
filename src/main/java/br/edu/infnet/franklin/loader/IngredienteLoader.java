package br.edu.infnet.franklin.loader;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.infnet.franklin.model.domain.Ingrediente;
import br.edu.infnet.franklin.model.domain.IngredienteLiquido;
import br.edu.infnet.franklin.model.domain.IngredienteSeco;
import br.edu.infnet.franklin.model.domain.IngredienteUnitario;
import br.edu.infnet.franklin.service.IngredienteService;

@Component
public class IngredienteLoader {

	@Autowired
    private IngredienteService ingredienteService;

	public void carregarIngredientes() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                this.getClass().getResourceAsStream("/data/ingredientes.txt")));

        String line;

		System.out.println("Carregando ingredientes...");

        while ((line = reader.readLine()) != null) {
            String[] campos = line.split(";");
            if(campos.length < 5) {
                // Tratar linhas com número insuficiente de campos
                continue;
            }

            Long id = Long.parseLong(campos[0]);
			String nome = campos[2];
			String tipo = campos[1];
			Double precoTotal = Double.valueOf(campos[3]);
		
			boolean organico = Boolean.parseBoolean(campos[4]);

			if(ingredienteService.obterPorId(id) != null) {
				System.out.println("Ingrediente " + nome + " já cadastrado.");
				continue;
			}

			System.out.println("Carregando ingrediente " + nome + "...");

            Ingrediente ingrediente = null;
            switch (tipo.toLowerCase()) {
                case "liquido":
                    IngredienteLiquido liquido = new IngredienteLiquido();
                    liquido.setVolumeLiquidoEmML(Double.parseDouble(campos[5]));
                    ingrediente = liquido;
                    break;
                case "unitario":
                    IngredienteUnitario unitario = new IngredienteUnitario();
                    unitario.setQuantidadeUnidades(Integer.parseInt(campos[5]));
                    ingrediente = unitario;
                    break;
                case "seco":
                default:
                    IngredienteSeco seco = new IngredienteSeco();
                    seco.setPesoLiquidoEmGramas(Double.parseDouble(campos[5]));
                    ingrediente = seco;
                    break;
            }

            ingrediente.setId(id);
            ingrediente.setNome(nome);
            ingrediente.setPrecoTotal(precoTotal);
            ingrediente.setOrganico(organico);

            ingredienteService.salvar(ingrediente);
        }

        reader.close();
    }
}
