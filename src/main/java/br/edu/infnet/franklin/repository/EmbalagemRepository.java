package br.edu.infnet.franklin.repository;

import br.edu.infnet.franklin.model.domain.Embalagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmbalagemRepository extends JpaRepository<Embalagem, Long> {

    Embalagem findByDescricao(String descricao);
}
