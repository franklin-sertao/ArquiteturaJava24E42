package br.edu.infnet.franklin.repository;

import br.edu.infnet.franklin.model.domain.ProdutoReceita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoReceitaRepository extends JpaRepository<ProdutoReceita, Long> {
    // Métodos adicionais se necessário
}
