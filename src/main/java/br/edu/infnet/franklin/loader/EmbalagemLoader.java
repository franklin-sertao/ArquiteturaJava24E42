package br.edu.infnet.franklin.loader;

import br.edu.infnet.franklin.model.domain.Embalagem;
import br.edu.infnet.franklin.service.EmbalagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component
public class EmbalagemLoader implements CommandLineRunner {

    @Autowired
    private EmbalagemService embalagemService;

    @Override
    public void run(String... args) throws Exception {
        carregarEmbalagens();
    }

    private void carregarEmbalagens() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                this.getClass().getResourceAsStream("/data/embalagens.txt")));

        String line;
        while ((line = reader.readLine()) != null) {
            String[] campos = line.split(";");
            Long id = Long.parseLong(campos[0]);
            String descricao = campos[1];
            Integer quantidadePorPacote = Integer.parseInt(campos[2]);
            java.math.BigDecimal precoPacote = new java.math.BigDecimal(campos[3]);

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
