package br.edu.infnet.franklin;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import br.edu.infnet.franklin.model.domain.Ingrediente;
import br.edu.infnet.franklin.model.domain.IngredienteLiquido;
import br.edu.infnet.franklin.model.domain.IngredienteSeco;
import br.edu.infnet.franklin.model.domain.IngredienteUnitario;
import br.edu.infnet.franklin.util.StringUtil;


@Component
public class Loader implements ApplicationRunner {
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		List<Ingrediente> ingredientes = getIngredientes();
		
		for (Ingrediente ingrediente : ingredientes) {
			System.out.println(ingrediente);
		}
	}
	
	static List<Ingrediente> getIngredientes() throws Exception {
		FileReader fileReader  = new FileReader("files/Ingredientes.txt");
		BufferedReader leitura = new BufferedReader(fileReader);
		
		String linha = leitura.readLine();
		
		List<Ingrediente> ingredientes = new ArrayList<>();
				
		while (linha != null) {
			
			String[] campos = linha.split(StringUtil.SEPARADOR,  StringUtil.SPLIT_CAMPOS_VAZIOS); 
			
			
			switch (campos[0]) { //un, ml, g
				case "un":
					IngredienteUnitario ingredienteUnitario = new IngredienteUnitario();
					ingredienteUnitario.setUnidadeMedida       (campos[0]);
					ingredienteUnitario.setNome                (campos[1]);
					ingredienteUnitario.setFabricante          (campos[2]);
					ingredienteUnitario.setDescricao           (campos[3]);
					ingredienteUnitario.setImagem              (campos[4]);
					ingredienteUnitario.setCodigo              (campos[5]);
					ingredienteUnitario.setQuantidadePorLote   (Integer.valueOf(campos[6]));
					ingredienteUnitario.setPreco(Float.valueOf (campos[7]));
					ingredienteUnitario.setTipo                (campos[8]);
					
					ingredientes.add(ingredienteUnitario);

					break;
				case "g":
					IngredienteSeco ingredienteSeco = new IngredienteSeco();
					ingredienteSeco.setUnidadeMedida       (campos[0]);
					ingredienteSeco.setNome                (campos[1]);
					ingredienteSeco.setFabricante          (campos[2]);
					ingredienteSeco.setDescricao           (campos[3]);
					ingredienteSeco.setImagem              (campos[4]);
					ingredienteSeco.setCodigo              (campos[5]);
					ingredienteSeco.setPeso(Float.valueOf  (campos[6]));
					ingredienteSeco.setPreco(Float.valueOf (campos[7]));
					ingredienteSeco.setTipo                (campos[8]);

					ingredientes.add(ingredienteSeco);

					break;

				case "ml":
					IngredienteLiquido ingredienteLiquido = new IngredienteLiquido();
					ingredienteLiquido.setUnidadeMedida       (campos[0]);
					ingredienteLiquido.setNome                (campos[1]);
					ingredienteLiquido.setFabricante          (campos[2]);
					ingredienteLiquido.setDescricao           (campos[3]);
					ingredienteLiquido.setImagem              (campos[4]);
					ingredienteLiquido.setCodigo              (campos[5]);
					ingredienteLiquido.setVolume(Float.valueOf(campos[6]));
					ingredienteLiquido.setPreco(Float.valueOf (campos[7]));
					ingredienteLiquido.setTipo                (campos[8]);
					
					ingredientes.add(ingredienteLiquido);

					break;

			}

			linha = leitura.readLine();
			
		}
		
		leitura.close();

		return ingredientes;

	}

}
