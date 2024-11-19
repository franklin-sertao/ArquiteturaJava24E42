package br.edu.infnet.franklin.repository;

import br.edu.infnet.franklin.model.domain.Produto;
import br.edu.infnet.franklin.model.domain.ProdutoReceita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoReceitaRepository extends JpaRepository<ProdutoReceita, Long> {
    void deleteByProduto(Produto produto);
    List<ProdutoReceita> findByProduto(Produto produto);
}
