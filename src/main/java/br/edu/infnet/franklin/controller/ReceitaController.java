package br.edu.infnet.franklin.controller;

import br.edu.infnet.franklin.model.domain.Receita;
import br.edu.infnet.franklin.service.ReceitaService;
import br.edu.infnet.franklin.service.IngredienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@Controller
@RequestMapping("/receitas")
public class ReceitaController {

    @Autowired
    private ReceitaService receitaService;

    @Autowired
    private IngredienteService ingredienteService;

    @GetMapping
    public String lista(Model model) {
        model.addAttribute("receitas", receitaService.obterLista());
        return "receitas/lista";
    }

    @GetMapping("/novo")
    public String nova(Model model) {
        model.addAttribute("receita", new Receita());
        model.addAttribute("ingredientes", ingredienteService.obterLista());
        return "receitas/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Receita receita,
                         @RequestParam Map<String, String> params) {
        // Extrai ingredientes e quantidades
        Map<Long, Double> ingredientesQuantidade = params.entrySet().stream()
                .filter(e -> e.getKey().startsWith("quantidade_"))
                .collect(
                        java.util.stream.Collectors.toMap(
                                e -> Long.parseLong(e.getKey().split("_")[1]),
                                e -> Double.parseDouble(e.getValue())
                        )
                );
        receitaService.salvar(receita, ingredientesQuantidade);
        return "redirect:/receitas";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        Receita receita = receitaService.obterPorId(id);
        model.addAttribute("receita", receita);
        model.addAttribute("ingredientes", ingredienteService.obterLista());
        return "receitas/formulario";
    }

    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id) {
        receitaService.excluir(id);
        return "redirect:/receitas";
    }
}
