package br.edu.infnet.franklin.repository;

import br.edu.infnet.franklin.model.domain.Receita;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceitaRepository extends CrudRepository<Receita, Long> {
}
