package br.edu.infnet.franklin.loader;

import br.edu.infnet.franklin.model.domain.Embalagem;
import br.edu.infnet.franklin.service.EmbalagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;

@Component
public class EmbalagemLoader {

    @Autowired
    private EmbalagemService embalagemService;

    @PostConstruct
    public void loadEmbalagens() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    getClass().getResourceAsStream("/data/Embalagens.txt"), "UTF-8"));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] campos = line.split(";");
                Embalagem embalagem = new Embalagem();
                embalagem.setDescricao(campos[0]);
                embalagem.setQuantidadePorPacote(Integer.parseInt(campos[1]));
                embalagem.setPrecoPacote(new BigDecimal(campos[2]));
                embalagemService.incluir(embalagem);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
