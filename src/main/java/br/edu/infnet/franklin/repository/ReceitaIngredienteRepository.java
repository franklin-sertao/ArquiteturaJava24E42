package br.edu.infnet.franklin.repository;

import br.edu.infnet.franklin.model.domain.Receita;
import br.edu.infnet.franklin.model.domain.ReceitaIngrediente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceitaIngredienteRepository extends CrudRepository<ReceitaIngrediente, Long> {
    void deleteByReceita(Receita receita);
    List<ReceitaIngrediente> findByReceita(Receita receita);
}
