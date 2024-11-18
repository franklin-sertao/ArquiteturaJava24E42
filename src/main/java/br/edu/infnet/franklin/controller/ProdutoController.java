package br.edu.infnet.franklin.controller;

import br.edu.infnet.franklin.model.domain.Produto;
import br.edu.infnet.franklin.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public void incluir(@RequestBody Produto produto) {
        produtoService.incluir(produto);
    }

    @GetMapping
    public List<Produto> obterLista() {
        return produtoService.obterLista();
    }

    @GetMapping("/{id}")
    public Produto obterPorId(@PathVariable Long id) {
        return produtoService.obterPorId(id);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        produtoService.excluir(id);
    }
}
