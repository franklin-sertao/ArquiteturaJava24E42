package br.edu.infnet.franklin.repository;

import br.edu.infnet.franklin.model.domain.Embalagem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmbalagemRepository extends CrudRepository<Embalagem, Long> {
}
