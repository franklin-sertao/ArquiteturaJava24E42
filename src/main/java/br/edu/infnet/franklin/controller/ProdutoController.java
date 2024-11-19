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

    @Autowired
    private EmbalagemService embalagemService;

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
        model.addAttribute("embalagens", embalagemService.obterLista());
        return "produtos/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(@RequestParam(required = false) Long id,
                         @ModelAttribute Produto produto,
                         @RequestParam Map<String, String> params) {
        // Extrai receitas e quantidades
        Map<Long, Double> receitasQuantidade = params.entrySet().stream()
                .filter(e -> e.getKey().startsWith("quantidade_receita_"))
                .collect(
                        java.util.stream.Collectors.toMap(
                                e -> Long.parseLong(e.getKey().split("_")[2]),
                                e -> Double.parseDouble(e.getValue())
                        )
                );

        // Extrai ingredientes e quantidades
        Map<Long, Double> ingredientesQuantidade = params.entrySet().stream()
                .filter(e -> e.getKey().startsWith("quantidade_ingrediente_"))
                .collect(
                        java.util.stream.Collectors.toMap(
                                e -> Long.parseLong(e.getKey().split("_")[2]),
                                e -> Double.parseDouble(e.getValue())
                        )
                );

        produtoService.salvar(id, produto, receitasQuantidade, ingredientesQuantidade);
        return "redirect:/produtos";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        Produto produto = produtoService.obterPorId(id);
        model.addAttribute("produto", produto);
        model.addAttribute("receitas", receitaService.obterLista());
        model.addAttribute("ingredientes", ingredienteService.obterLista());
        model.addAttribute("embalagens", embalagemService.obterLista());
        return "produtos/formulario";
    }

    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id) {
        produtoService.excluir(id);
        return "redirect:/produtos";
    }
}
