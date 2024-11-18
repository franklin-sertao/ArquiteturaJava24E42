package br.edu.infnet.franklin.service;

import br.edu.infnet.franklin.model.domain.Receita;
import br.edu.infnet.franklin.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceitaService {

    @Autowired
    private ReceitaRepository receitaRepository;

    public void incluir(Receita receita) {
        receitaRepository.save(receita);
    }

    public List<Receita> obterLista() {
        return receitaRepository.findAll();
    }

    public void excluir(Long id) {
        receitaRepository.deleteById(id);
    }

    public Receita findByNome(String nome) {
        return receitaRepository.findByNome(nome);
    }
}