package br.edu.infnet.franklin.repository;

import br.edu.infnet.franklin.model.domain.ProdutoIngrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoIngredienteRepository extends JpaRepository<ProdutoIngrediente, Long> {
    // Métodos adicionais se necessário
}
