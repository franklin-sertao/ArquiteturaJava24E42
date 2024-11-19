package br.edu.infnet.franklin.loader;

import br.edu.infnet.franklin.model.domain.*;
import br.edu.infnet.franklin.service.IngredienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component
public class IngredienteLoader implements CommandLineRunner {

    @Autowired
    private IngredienteService ingredienteService;

    @Override
    public void run(String... args) throws Exception {
        carregarIngredientes();
    }

    private void carregarIngredientes() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                this.getClass().getResourceAsStream("/data/ingredientes.txt")));

        String line;
        while ((line = reader.readLine()) != null) {
            String[] campos = line.split(";");
            if(campos.length < 5) {
                // Tratar linhas com número insuficiente de campos
                continue;
            }

            Long id = Long.parseLong(campos[0]);
            String tipo = campos[1];
            String nome = campos[2];
            java.math.BigDecimal precoTotal = new java.math.BigDecimal(campos[3]);
            boolean organico = Boolean.parseBoolean(campos[4]);

            Ingrediente ingrediente = null;
            switch (tipo.toLowerCase()) {
                case "liquido":
                    if(campos.length < 6) continue; // Campo específico ausente
                    IngredienteLiquido liquido = new IngredienteLiquido();
                    liquido.setVolumeLiquidoEmML(Integer.parseInt(campos[5]));
                    ingrediente = liquido;
                    break;
                case "unitario":
                    if(campos.length < 6) continue; // Campo específico ausente
                    IngredienteUnitario unitario = new IngredienteUnitario();
                    unitario.setQuantidadeUnidades(Integer.parseInt(campos[5]));
                    ingrediente = unitario;
                    break;
                case "seco":
                default:
                    if(campos.length < 6) continue; // Campo específico ausente
                    IngredienteSeco seco = new IngredienteSeco();
                    seco.setPesoLiquidoEmGramas(Integer.parseInt(campos[5]));
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
