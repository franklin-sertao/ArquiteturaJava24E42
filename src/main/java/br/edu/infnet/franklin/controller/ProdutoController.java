package br.edu.infnet.franklin.controller;

import br.edu.infnet.franklin.model.domain.Produto;
import br.edu.infnet.franklin.service.ProdutoService;
import br.edu.infnet.franklin.service.ReceitaService;
import br.edu.infnet.franklin.service.IngredienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ReceitaService receitaService;

    @Autowired
    private IngredienteService ingredienteService;

    @GetMapping
    public String lista(Model model) {
        model.addAttribute("produtos", produtoService.obterLista());
        return "produtos/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("produto", new Produto());
        model.addAttribute("receitas", receitaService.obterLista());
        model.addAttribute("ingredientes", ingredienteService.obterLista());
        return "produtos/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(@RequestParam(required = false) Long id,
                         @ModelAttribute Produto produto,
                         @RequestParam Map<Long, Double> receitasQuantidade,
                         @RequestParam Map<Long, Double> ingredientesQuantidade) {
        produtoService.salvar(id, produto, receitasQuantidade, ingredientesQuantidade);
        return "redirect:/produtos";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        Produto produto = produtoService.obterPorId(id);
        model.addAttribute("produto", produto);
        model.addAttribute("receitas", receitaService.obterLista());
        model.addAttribute("ingredientes", ingredienteService.obterLista());
        return "produtos/formulario";
    }

    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id) {
        produtoService.excluir(id);
        return "redirect:/produtos";
    }
}
