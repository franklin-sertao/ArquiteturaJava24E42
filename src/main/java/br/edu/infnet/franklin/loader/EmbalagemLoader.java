package br.edu.infnet.franklin.loader;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.infnet.franklin.model.domain.Embalagem;
import br.edu.infnet.franklin.service.EmbalagemService;

@Component
public class EmbalagemLoader {

    @Autowired
    EmbalagemService embalagemService;

    public void carregarEmbalagens() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                this.getClass().getResourceAsStream("/data/embalagens.txt")));

        String line;
        while ((line = reader.readLine()) != null) {
            
			String[] campos = line.split(";");

            Long id = Long.parseLong(campos[0]);
			String descricao = campos[1];
            Integer quantidadePorPacote = Integer.parseInt(campos[2]);
            java.math.BigDecimal precoPacote = new java.math.BigDecimal(campos[3]);

			if(embalagemService.obterPorId(id) != null) {
				System.out.println("Embalagem " + descricao + " já cadastrada.");
				continue;
			}

			System.out.println("Carregando embalagem " + descricao + "...");
			
            Embalagem embalagem = new Embalagem();
            embalagem.setId(id);
            embalagem.setDescricao(descricao);
            embalagem.setQuantidadePorPacote(quantidadePorPacote);
            embalagem.setPrecoPacote(precoPacote);

            embalagemService.salvar(id, embalagem);
        }

        reader.close();
    }
}
