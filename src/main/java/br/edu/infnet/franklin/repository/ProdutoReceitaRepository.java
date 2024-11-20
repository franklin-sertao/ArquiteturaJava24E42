package br.edu.infnet.franklin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.edu.infnet.franklin.model.domain.Produto;
import br.edu.infnet.franklin.model.domain.ProdutoReceita;
import jakarta.transaction.Transactional;

@Repository
public interface ProdutoReceitaRepository extends CrudRepository<ProdutoReceita, Long> {
	
	@Transactional
	@Modifying
	void deleteByProdutoId(Long id);
    List<ProdutoReceita> findByProduto(Produto produto);
}

