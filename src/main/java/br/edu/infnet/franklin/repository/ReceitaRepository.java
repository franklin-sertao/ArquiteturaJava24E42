package br.edu.infnet.franklin.repository;

import br.edu.infnet.franklin.model.domain.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long> {
    Receita findByNome(String nome);
}
