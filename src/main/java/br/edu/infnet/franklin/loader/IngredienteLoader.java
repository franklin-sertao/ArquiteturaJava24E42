package br.edu.infnet.franklin.loader;

import br.edu.infnet.franklin.model.domain.*;
import br.edu.infnet.franklin.service.IngredienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;

@Component
public class IngredienteLoader {

    @Autowired
    private IngredienteService ingredienteService;

    @PostConstruct
    public void loadIngredientes() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    getClass().getResourceAsStream("/data/Ingredientes.txt"), "UTF-8"));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] campos = line.split(";");
                String tipo = campos[0];
                Ingrediente ingrediente = null;

                switch (tipo.toUpperCase()) {
                    case "S":
                        ingrediente = new IngredienteSeco();
                        ((IngredienteSeco) ingrediente).setPesoLiquidoEmGramas(Integer.parseInt(campos[4]));
                        break;
                    case "U":
                        ingrediente = new IngredienteUnitario();
                        ((IngredienteUnitario) ingrediente).setQuantidadeUnidades(Integer.parseInt(campos[4]));
                        break;
                    case "L":
                        ingrediente = new IngredienteLiquido();
                        ((IngredienteLiquido) ingrediente).setVolumeLiquidoEmML(Integer.parseInt(campos[4]));
                        break;
                    default:
                        System.out.println("Tipo de ingrediente desconhecido: " + tipo);
                        continue;
                }

                ingrediente.setNome(campos[1]);
                ingrediente.setPrecoTotal(new BigDecimal(campos[2]));
                ingrediente.setOrganico(Boolean.parseBoolean(campos[3]));

                ingredienteService.incluir(ingrediente);
            }

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}