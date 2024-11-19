package br.edu.infnet.franklin.repository;

import br.edu.infnet.franklin.model.domain.Receita;
import br.edu.infnet.franklin.model.domain.ReceitaIngrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceitaIngredienteRepository extends JpaRepository<ReceitaIngrediente, Long> {
    void deleteByReceita(Receita receita);
    List<ReceitaIngrediente> findByReceita(Receita receita);
}
