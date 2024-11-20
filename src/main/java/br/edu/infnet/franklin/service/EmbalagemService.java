package br.edu.infnet.franklin.service;

import br.edu.infnet.franklin.model.domain.Embalagem;
import br.edu.infnet.franklin.repository.EmbalagemRepository;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmbalagemService {

    @Autowired
    private EmbalagemRepository embalagemRepository;

    public Embalagem salvar(Long id, Embalagem embalagem) {
        if (id != null && embalagemRepository.existsById(id)) {
            Embalagem embalagemExistente = embalagemRepository.findById(id).orElse(null);
            if (embalagemExistente != null) {
                embalagemExistente.setDescricao(embalagem.getDescricao());
                embalagemExistente.setQuantidadePorPacote(embalagem.getQuantidadePorPacote());
                embalagemExistente.setPrecoPacote(embalagem.getPrecoPacote());
                return embalagemRepository.save(embalagemExistente);
            }
        }
        return embalagemRepository.save(embalagem);
    }

	@Transactional
    public void excluir(Long id) {
        embalagemRepository.deleteById(id);
    }

    public Embalagem obterPorId(Long id) {
        return embalagemRepository.findById(id).orElse(null);
    }

    public Iterable<Embalagem> obterLista() {
        return embalagemRepository.findAll();
    }
}
