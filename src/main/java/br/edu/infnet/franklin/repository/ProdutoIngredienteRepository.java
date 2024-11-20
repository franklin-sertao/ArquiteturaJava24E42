package br.edu.infnet.franklin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.edu.infnet.franklin.model.domain.Produto;
import br.edu.infnet.franklin.model.domain.ProdutoIngrediente;
import jakarta.transaction.Transactional;

@Repository
public interface ProdutoIngredienteRepository extends CrudRepository<ProdutoIngrediente, Long> {
	
	@Transactional
	@Modifying
    void deleteByProdutoId(Long id);
    List<ProdutoIngrediente> findByProduto(Produto produto);
}
