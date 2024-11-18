package br.edu.infnet.franklin.controller;

import br.edu.infnet.franklin.model.domain.Embalagem;
import br.edu.infnet.franklin.service.EmbalagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/embalagens")
public class EmbalagemController {

    @Autowired
    private EmbalagemService embalagemService;

    @PostMapping
    public void incluir(@RequestBody Embalagem embalagem) {
        embalagemService.incluir(embalagem);
    }

    @GetMapping
    public List<Embalagem> obterLista() {
        return embalagemService.obterLista();
    }

    @GetMapping("/{id}")
    public Embalagem obterPorId(@PathVariable Long id) {
        return embalagemService.obterPorId(id);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        embalagemService.excluir(id);
    }
}
