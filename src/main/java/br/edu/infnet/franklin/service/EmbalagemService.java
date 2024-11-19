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

    public void salvar(Embalagem embalagem) {
        embalagemRepository.save(embalagem);
    }

    public List<Embalagem> obterLista() {
        return embalagemRepository.findAll();
    }

    public Embalagem obterPorId(Long id) {
        return embalagemRepository.findById(id).orElse(null);
    }

    public void excluir(Long id) {
        embalagemRepository.deleteById(id);
    }

    public Embalagem obterPorDescricao(String descricao) {
        return embalagemRepository.findByDescricao(descricao);
    }
}
