package br.edu.infnet.franklin.controller;

import br.edu.infnet.franklin.model.domain.Embalagem;
import br.edu.infnet.franklin.model.domain.Ingrediente;
import br.edu.infnet.franklin.model.domain.Produto;
import br.edu.infnet.franklin.model.domain.Receita;
import br.edu.infnet.franklin.service.EmbalagemService;
import br.edu.infnet.franklin.service.IngredienteService;
import br.edu.infnet.franklin.service.ProdutoService;
import br.edu.infnet.franklin.service.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.stream.Collectors;

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
                         @RequestParam Map<String, String> allRequestParams) {

        // Processar receitas e quantidades
        Map<Long, Double> receitasQuantidade = allRequestParams.entrySet().stream()
                .filter(e -> e.getKey().startsWith("receita_"))
                .collect(Collectors.toMap(
                        e -> Long.parseLong(e.getKey().replace("receita_", "")),
                        e -> Double.parseDouble(e.getValue())
                ));

        // Processar ingredientes e quantidades
        Map<Long, Double> ingredientesQuantidade = allRequestParams.entrySet().stream()
                .filter(e -> e.getKey().startsWith("ingrediente_"))
                .collect(Collectors.toMap(
                        e -> Long.parseLong(e.getKey().replace("ingrediente_", "")),
                        e -> Double.parseDouble(e.getValue())
                ));

        // Processar embalagens
        List<Long> embalagensIds = allRequestParams.entrySet().stream()
                .filter(e -> e.getKey().startsWith("embalagem_"))
                .map(e -> Long.parseLong(e.getKey().replace("embalagem_", "")))
                .collect(Collectors.toList());

        Set<Embalagem> embalagens = new HashSet<>();
        for (Long id : embalagensIds) {
            Embalagem embalagem = embalagemService.obterPorId(id);
            if (embalagem != null) {
                embalagens.add(embalagem);
            }
        }
        produto.setEmbalagens(embalagens);

        produtoService.salvar(produto, receitasQuantidade, ingredientesQuantidade);
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
