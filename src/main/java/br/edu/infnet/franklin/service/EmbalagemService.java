package br.edu.infnet.franklin.service;

import br.edu.infnet.franklin.model.domain.Embalagem;
import br.edu.infnet.franklin.repository.EmbalagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmbalagemService {

    @Autowired
    private EmbalagemRepository embalagemRepository;

    public void salvar(Long id, Embalagem embalagem) {
        if (id != null && embalagemRepository.existsById(id)) {
            Embalagem embalagemExistente = embalagemRepository.findById(id).orElse(null);
            if (embalagemExistente != null) {
                embalagemExistente.setDescricao(embalagem.getDescricao());
                embalagemExistente.setQuantidadePorPacote(embalagem.getQuantidadePorPacote());
                embalagemExistente.setPrecoPacote(embalagem.getPrecoPacote());
                embalagemRepository.save(embalagemExistente);
            }
        } else {
            embalagemRepository.save(embalagem);
        }
    }

    public void excluir(Long id) {
        embalagemRepository.deleteById(id);
    }

    public Embalagem obterPorId(Long id) {
        return embalagemRepository.findById(id).orElse(null);
    }

    public List<Embalagem> obterLista() {
        return embalagemRepository.findAll();
    }
}
