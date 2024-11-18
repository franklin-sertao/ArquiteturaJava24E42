package br.edu.infnet.franklin.repository;

import br.edu.infnet.franklin.model.domain.ReceitaIngrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceitaIngredienteRepository extends JpaRepository<ReceitaIngrediente, Long> {
    // Métodos adicionais se necessário
}
