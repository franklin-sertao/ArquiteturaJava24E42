package br.edu.infnet.franklin.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.infnet.franklin.model.domain.Produto;
import br.edu.infnet.franklin.service.EmbalagemService;
import br.edu.infnet.franklin.service.IngredienteService;
import br.edu.infnet.franklin.service.ProdutoService;
import br.edu.infnet.franklin.service.ReceitaService;

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
	public String salvar(@RequestParam(required = false) Long id, @ModelAttribute Produto produto, @RequestParam Map<String, String> params) {
    // Extrai receitas e quantidades usando índices de array
    Map<Long, Double> receitasQuantidade = params.entrySet().stream()
            .filter(e -> e.getKey().matches("receitas\\[\\d+\\]\\.id"))
            .collect(
                    java.util.stream.Collectors.toMap(
                            e -> Long.parseLong(params.get(e.getKey())),
                            e -> Double.parseDouble(params.get(e.getKey().replace(".id", ".quantidade")))
                    )
            );

    // Extrai ingredientes e quantidades usando índices de array
    Map<Long, Double> ingredientesQuantidade = params.entrySet().stream()
            .filter(e -> e.getKey().matches("ingredientes\\[\\d+\\]\\.id"))
            .collect(
                    java.util.stream.Collectors.toMap(
                            e -> Long.parseLong(params.get(e.getKey())),
                            e -> Double.parseDouble(params.get(e.getKey().replace(".id", ".quantidade")))
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
