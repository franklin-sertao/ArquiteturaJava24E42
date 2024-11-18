package br.edu.infnet.franklin.repository;

import br.edu.infnet.franklin.model.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    Produto findByDescricao(String descricao);
}
