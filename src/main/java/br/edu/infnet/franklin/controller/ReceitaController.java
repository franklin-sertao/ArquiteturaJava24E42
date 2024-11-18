package br.edu.infnet.franklin.controller;

import br.edu.infnet.franklin.model.domain.Produto;
import br.edu.infnet.franklin.service.ProdutoService;
import br.edu.infnet.franklin.service.ReceitaService;
import br.edu.infnet.franklin.service.IngredienteService;
import br.edu.infnet.franklin.service.EmbalagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ReceitaService receitaService;

    @Autowired
    private IngredienteService ingredienteService;

    @Autowired
    private EmbalagemService embalagemService;

    @GetMapping
    public String lista(Model model) {
        List<Produto> produtos = produtoService.obterLista();
        model.addAttribute("produtos", produtos);
        return "produtos/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("produto", new Produto());
        model.addAttribute("receitas", receitaService.obterLista());
        model.addAttribute("ingredientes", ingredienteService.obterLista());
        model.addAttribute("embalagens", embalagemService.obterLista());
        return "produtos/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Produto produto,
                         @RequestParam("receitasIds") List<Long> receitasIds,
                         @RequestParam("quantidadesReceitas") List<Double> quantidadesReceitas,
                         @RequestParam("ingredientesIds") List<Long> ingredientesIds) {
        produtoService.incluir(produto, receitasIds, quantidadesReceitas, ingredientesIds);
        return "redirect:/produtos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Produto produto = produtoService.obterPorId(id);
        model.addAttribute("produto", produto);
        model.addAttribute("receitas", receitaService.obterLista());
        model.addAttribute("ingredientes", ingredienteService.obterLista());
        model.addAttribute("embalagens", embalagemService.obterLista());
        return "produtos/formulario";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        produtoService.excluir(id);
        return "redirect:/produtos";
    }
}
