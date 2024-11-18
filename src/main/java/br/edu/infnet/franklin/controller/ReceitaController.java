package br.edu.infnet.franklin.controller;

import br.edu.infnet.franklin.model.domain.Ingrediente;
import br.edu.infnet.franklin.service.IngredienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredientes")
public class IngredienteController {

    @Autowired
    private IngredienteService ingredienteService;

    @PostMapping
    public void incluir(@RequestBody Ingrediente ingrediente) {
        ingredienteService.incluir(ingrediente);
    }

    @GetMapping
    public List<Ingrediente> obterLista() {
        return ingredienteService.obterLista();
    }

    @GetMapping("/{id}")
    public Ingrediente obterPorId(@PathVariable Long id) {
        return ingredienteService.obterPorId(id);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        ingredienteService.excluir(id);
    }
}
