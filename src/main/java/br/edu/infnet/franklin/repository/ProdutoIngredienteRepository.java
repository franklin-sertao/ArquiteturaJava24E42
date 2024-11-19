package br.edu.infnet.franklin.repository;

import br.edu.infnet.franklin.model.domain.Produto;
import br.edu.infnet.franklin.model.domain.ProdutoIngrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoIngredienteRepository extends JpaRepository<ProdutoIngrediente, Long> {
    void deleteByProduto(Produto produto);
    List<ProdutoIngrediente> findByProduto(Produto produto);
}
